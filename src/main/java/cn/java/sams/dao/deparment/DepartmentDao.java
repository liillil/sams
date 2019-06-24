package cn.java.sams.dao.deparment;

import java.util.List;

import cn.java.sams.dao.BaseDao;
import cn.java.sams.model.Department;
import cn.java.sams.model.Pager;

public interface DepartmentDao extends BaseDao<Department>{
	
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
