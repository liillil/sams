package cn.java.sams.dao.courses;

import java.util.List;

import cn.java.sams.dao.BaseDao;
import cn.java.sams.model.Courses;
import cn.java.sams.model.StudentCourses;
import cn.java.sams.model.Teacher;

public interface StudentCoursesDao extends BaseDao<StudentCourses>{

	/**
	 * 通过开课时间，课程，任课教师查询StudentCourses
	 * @param sDate
	 * @param courses
	 * @param teacher
	 * @return
	 */
	StudentCourses getStudentCourses(String sDate, String coursesName, Teacher teacher);
	
}
