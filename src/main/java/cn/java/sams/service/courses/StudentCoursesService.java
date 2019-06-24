package cn.java.sams.service.courses;


import cn.java.sams.model.Courses;
import cn.java.sams.model.StudentCourses;
import cn.java.sams.model.Teacher;
import cn.java.sams.service.BaseService;

public interface StudentCoursesService extends BaseService<StudentCourses>{
	/**
	 * 通过开课时间，课程，任课教师查询StudentCourses
	 * @param sDate
	 * @param courses
	 * @param teacher
	 * @return
	 */
	StudentCourses getStudentCourses(String sDate, String coursesName, Teacher teacher);
	
}
