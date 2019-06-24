package cn.java.sams.service.role_permission.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.sams.dao.role_permission.PermissionDao;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Permission;
import cn.java.sams.service.impl.BaseServiceImpl;
import cn.java.sams.service.role_permission.PermissionService;

@Service(value="permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService{
	@Autowired
	private PermissionDao pd;
	
	@Override
	public List<Permission> getAllPermission() {
		
		return pd.getAllPermission();
	}

	@Override
	public Pager<Permission> getAllPermissionByPage(Permission permission, int pageNumber, int pageSize) {
		return pd.getAllPermissionByPage(permission, pageNumber, pageSize);
	}

	/**
	 * InitWebServlet初始化时，获取到的权限的标记list，写入数据库
	 * 对于同样的标记只写入数据库一次
	 */
	@Override
	public void InitPermissions(List<String> resources) {
		boolean isExist = false;
		for(String resource : resources) {
			isExist = pd.isExistResource(resource);
			if(!isExist) {//不存在执行插入
				System.out.println("*******************************"+resource);
				Permission permission = new Permission();
				permission.setResource(resource);
				permission.setStatus(1);
				pd.add(permission);
			}
		}
		
	}
}
