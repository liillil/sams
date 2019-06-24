package cn.java.sams.service.courses;


import java.util.List;

import cn.java.sams.model.Courses;
import cn.java.sams.model.Pager;
import cn.java.sams.service.BaseService;

public interface CoursesService extends BaseService<Courses>{
	/**
	 * 获取全部课程信息
	 * @return
	 */
	public List<Courses> getAllCourses();
	
	/**
	 * 分页获取全部课程信息
	 * @param courses
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<Courses> getAllCoursesByPage(Courses courses, Integer page, Integer rows);
	
	/**
	 * 通过名称查询课程
	 * @param coursesName
	 * @return
	 */
	public Courses getCoursesByName(String coursesName);
}
