package cn.java.sams.service.role_permission;

import java.util.List;

import cn.java.sams.model.Pager;
import cn.java.sams.model.Permission;
import cn.java.sams.service.BaseService;

public interface PermissionService extends BaseService<Permission>{
	
	public List<Permission> getAllPermission();
	
	public Pager<Permission> getAllPermissionByPage(Permission permission,int pageNumber,int pageSize);
	
	public void InitPermissions(List<String> resources);
}
