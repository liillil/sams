package cn.java.sams.dao.deparment.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.java.sams.dao.deparment.DepartmentDao;
import cn.java.sams.dao.impl.BaseDaoImpl;
import cn.java.sams.model.Department;
import cn.java.sams.model.Major;
import cn.java.sams.model.Pager;
@Repository(value="departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao{

	@Override
	public List<Department> getAllDepartment() {
		String jpql = "from Department";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public Pager<Department> getAllDepartmentByPage(Department department, Integer page, Integer rows) {
		Pager<Department> pager = new Pager<>();
		String jpql = "select d from Department d where 1=1";
		String countJpql = "select count(d) from Department d where 1=1";
		String str = "";
		if(department.getDepartmentNum() != null && department.getDepartmentNum() != "") {
			str += " and d.departmentNum like '%"+department.getDepartmentNum().trim()+"%'";
		}
		if(department.getDepartmentName() != null && department.getDepartmentName() != "") {
			str += " and d.departmentName like '%"+department.getDepartmentName().trim()+"%'";
		}
			
		jpql = jpql+str;
		countJpql +=str;
		System.out.println("==========================="+jpql);
		List<Department> departments = em.createQuery(jpql).setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
		long count = (long) em.createQuery(countJpql).getSingleResult();
		int coun = (int) count;
		pager.setRows(departments);
		pager.setTotal(coun);
		pager.setOffset(page);
		pager.setSize(rows);
	return pager;
	}

}
