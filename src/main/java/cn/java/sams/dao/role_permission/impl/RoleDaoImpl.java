package cn.java.sams.dao.role_permission.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.java.sams.dao.impl.BaseDaoImpl;
import cn.java.sams.dao.role_permission.RoleDao;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Role;


@Repository(value="roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRole() {
		String jpql = "from Role r where r.status=1";
		return em.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Role> getRoleByPage(Role role, int pageNumber, int pageSize) {
		String jpql = "select r from Role r where 1=1 ";
		String countJpql = "select count(r) from Role r where 1=1";
		String str = "";
		if(role != null) {
			
		}
		jpql = jpql+str;
		countJpql +=str;
		List<Role> roles= em.createQuery(jpql).setFirstResult((pageNumber-1)*pageSize).setMaxResults(pageSize).getResultList();
		Object obj =  em.createQuery(countJpql).getSingleResult();
		int count = Integer.parseInt(obj.toString());
		Pager<Role> pager = new Pager<>();
		pager.setRows(roles);
		pager.setSize(pageSize);
		pager.setTotal(count);
		pager.setOffset(pageNumber);
		return pager;
	}

}
