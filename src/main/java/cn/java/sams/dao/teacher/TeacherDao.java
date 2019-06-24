package cn.java.sams.dao.teacher;

import cn.java.sams.dao.BaseDao;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Student_StudentCourses;
import cn.java.sams.model.Teacher;
import cn.java.sams.model.TeacherCourses;

public interface TeacherDao extends BaseDao<Teacher>{
	public Pager<Teacher> getTeacherByPage(Teacher teacher,Integer page,Integer rows);

	public void updateTeacher(Teacher teacher);

	public Pager<Student_StudentCourses> getTeacherCoursesByPage(TeacherCourses teacherCourses, Integer page, Integer rows);
	
//	public Pager<T> TeacherCourses();
	
	public Teacher getTeacherByTeacherNum(String  teacherNum);
}
