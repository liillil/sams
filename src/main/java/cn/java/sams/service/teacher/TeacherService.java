package cn.java.sams.service.teacher;


import java.util.Map;

import cn.java.sams.model.Pager;
import cn.java.sams.model.Student_StudentCourses;
import cn.java.sams.model.Teacher;
import cn.java.sams.model.TeacherCourses;
import cn.java.sams.service.BaseService;


public interface TeacherService extends BaseService<Teacher>{
	/**
	 * 分页查询信息查询
	 * @param teacher
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<Teacher> getTeacherByPage(Teacher teacher, Integer page, Integer rows);
	/**
	 * 修改课程信息
	 * @param teacher
	 */
	public  void updateTeacher(Teacher teacher);
	/**
	 * 教师任课信息查询
	 * @param teacherCourses
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<TeacherCourses> getTeacherCoursesByPage(TeacherCourses teacherCourses, Integer page, Integer rows);

//	public void deleteTeacherCourses(Map<String,String> id);

	/**
	 * 添加教师任课安排
	 * @param teacherCourses
	 */
	public void addTeacherCoursses(TeacherCourses teacherCourses);
	
	/**
	 * 修改教师任课安排
	 * @param teacherCourses
	 */
	public void updateTeacherCourses(TeacherCourses teacherCourses);
	/**
	 * 通过教师编号查询教师
	 * @param teacherNum
	 * @return
	 */
	public Teacher getTeacherByTeacherNum(String  teacherNum);
	
	/**
	 * 登录
	 * @param teacherNum
	 * @param password
	 * @return
	 */
	public Teacher  login(String teacherNum, String password);
}
