package cn.java.sams.dao.role_permission.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.java.sams.dao.impl.BaseDaoImpl;
import cn.java.sams.dao.role_permission.PermissionDao;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Permission;



@Repository(value="/permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getAllPermission() {
		String jpql = "from Permission";
		return em.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Permission> getAllPermissionByPage(Permission permission, int pageNumber, int pageSize) {
		String jpql = "select p from Permission p where 1=1 ";
		String countJpql = "select count(p) from Permission p where 1=1";
		String str = "";
		if(permission != null) {
			
		}
		jpql = jpql+str;
		countJpql +=str;
		List<Permission> permissions= em.createQuery(jpql).setFirstResult((pageNumber-1)*pageSize).setMaxResults(pageSize).getResultList();
		Object obj =  em.createQuery(countJpql).getSingleResult();
		int count = Integer.parseInt(obj.toString());
		Pager<Permission> pager = new Pager<>();
		pager.setRows(permissions);
		pager.setSize(pageSize);
		pager.setTotal(count);
		pager.setOffset(pageNumber);
		return pager;
	}

	@Override
	public boolean isExistResource(String resource) {
		String jpql = "select count(*) from Permission p where p.resource =?1";
		long count = (long) em.createQuery(jpql).setParameter(1, resource).getSingleResult();
		return count>0?true:false;
	}

}
