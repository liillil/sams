package cn.java.sams.service.courses.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.sams.dao.courses.CoursesDao;
import cn.java.sams.model.Courses;
import cn.java.sams.model.Pager;
import cn.java.sams.service.courses.CoursesService;
import cn.java.sams.service.impl.BaseServiceImpl;
@Service(value="coursesService")
public class CoursesServiceImpl extends BaseServiceImpl<Courses> implements CoursesService{
	@Autowired
	private CoursesDao cd;
	
	public List<Courses> getAllCourses() {
		return cd.getAllCourses();
	}

	@Override
	public Pager<Courses> getAllCoursesByPage(Courses courses, Integer page, Integer rows) {
		
		return cd.getAllCoursesByPage(courses,page,rows);
	}

	@Override
	public Courses getCoursesByName(String coursesName) {
		return cd.getCoursesByName(coursesName);
	}
}
