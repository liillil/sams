package cn.java.sams.dao.courses.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.java.sams.dao.courses.StudentCoursesDao;
import cn.java.sams.dao.impl.BaseDaoImpl;
import cn.java.sams.model.Courses;
import cn.java.sams.model.StudentCourses;
import cn.java.sams.model.Teacher;
import cn.java.sams.util.StringTransferDate;
@Repository(value="studentCoursesDao")
public class StudentCoursesDaoImpl extends BaseDaoImpl<StudentCourses> implements StudentCoursesDao{

	@Override
	public StudentCourses getStudentCourses(String sDate, String coursesName, Teacher teacher) {
		String jpql = "select s from StudentCourses s where s.sDate = '"+sDate+"' and s.courses.coursesName = '"+coursesName+"'";
		if(teacher != null) {
			jpql += " and s.teacher = "+teacher.getId();
		}
		StudentCourses studentCourses = (StudentCourses) em.createQuery(jpql).getSingleResult();
		return studentCourses;
	}

}
