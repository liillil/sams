package cn.java.sams.service.courses.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.sams.dao.courses.StudentCoursesDao;
import cn.java.sams.model.Courses;
import cn.java.sams.model.StudentCourses;
import cn.java.sams.model.Teacher;
import cn.java.sams.service.courses.StudentCoursesService;
import cn.java.sams.service.impl.BaseServiceImpl;
@Service(value="studentCoursesService")
public class StudentCoursesServiceImpl extends BaseServiceImpl<StudentCourses> implements StudentCoursesService{

	@Autowired
	private StudentCoursesDao scd;
	
	@Override
	public StudentCourses getStudentCourses(String sDate, String coursesName, Teacher teacher) {
		
		return scd.getStudentCourses(sDate,coursesName,teacher);
	}

}
