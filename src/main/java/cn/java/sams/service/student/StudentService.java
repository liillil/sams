package cn.java.sams.service.student;


import java.util.List;

import cn.java.sams.model.Major;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Student;
import cn.java.sams.model.Student_StudentCourses;
import cn.java.sams.service.BaseService;

public interface StudentService extends BaseService<Student>{

	Pager<Student> getAllStudentByPage(Student student, Integer page, Integer rows);

	/**
	 * 获取班级编号
	 * @param major
	 * @return
	 */
	List<String> getClassNum(int id);

	/**
	 * 通过班级编号和专业获取学生信息
	 * @param classNum
	 * @param major
	 * @return
	 */
	List<Student> getStudentByClassNum(String classNum, Integer majorId);

}
