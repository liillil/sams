package cn.java.sams.service.role_permission.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.sams.dao.role_permission.RoleDao;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Role;
import cn.java.sams.service.impl.BaseServiceImpl;
import cn.java.sams.service.role_permission.RoleService;
@Service(value="roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	@Autowired
	private RoleDao rd;
	@Override
	public List<Role> getAllRole() {
		
		return rd.getAllRole();
	}
	@Override
	public Pager<Role> getRoleByPage(Role role, int pageNumber, int pageSize) {
		return rd.getRoleByPage(role, pageNumber, pageSize);
	}

}
