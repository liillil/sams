package cn.java.sams.service.department.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.sams.dao.deparment.DepartmentDao;
import cn.java.sams.model.Department;
import cn.java.sams.model.Pager;
import cn.java.sams.service.department.DepartmentService;
import cn.java.sams.service.impl.BaseServiceImpl;
@Service(value="departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService{

	@Autowired
	private DepartmentDao dd;
	
	@Override
	public List<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		return dd.getAllDepartment();
	}

	@Override
	public Pager<Department> getAllDepartmentByPage(Department department, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return dd.getAllDepartmentByPage(department, page, rows);
	}

}
