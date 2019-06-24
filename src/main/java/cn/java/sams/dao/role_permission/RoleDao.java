package cn.java.sams.dao.role_permission;

import java.util.List;

import cn.java.sams.dao.BaseDao;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Role;




public interface RoleDao extends BaseDao<Role>{
	/**
	 * 获取全部角色信息
	 * @return
	 */
	public List<Role> getAllRole();
	/**
	 * 分页获取全部角色信息
	 * @param user
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Pager<Role> getRoleByPage(Role role,int pageNumber,int pageSize);
}
