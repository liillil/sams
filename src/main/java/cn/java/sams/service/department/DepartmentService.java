package cn.java.sams.service.department;

import java.util.List;

import cn.java.sams.model.Department;
import cn.java.sams.model.Pager;
import cn.java.sams.service.BaseService;

public interface DepartmentService extends BaseService<Department>{
	/**
	 * 获取全部院系信息
	 * @return
	 */
	public List<Department> getAllDepartment();

	/**
	 * 分页获取全部院系信息
	 * @return
	 */
	public Pager<Department> getAllDepartmentByPage(Department department, Integer page, Integer rows);

}
