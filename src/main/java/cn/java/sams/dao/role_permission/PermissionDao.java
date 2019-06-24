package cn.java.sams.dao.role_permission;

import java.util.List;

import cn.java.sams.dao.BaseDao;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Permission;



public interface PermissionDao extends BaseDao<Permission>{
	
	public List<Permission> getAllPermission();
	
	public Pager<Permission> getAllPermissionByPage(Permission permission,int pageNumber,int pageSize);
	/**
	 * 判断权限标记是否已经在数据库中
	 * @param resource
	 * @return 存在返回true，否者返回false
	 */
	public boolean isExistResource(String resource);
}
