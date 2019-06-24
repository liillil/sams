package cn.java.sams.service.student.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.sams.dao.student.StudentDao;
import cn.java.sams.model.Major;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Student;
import cn.java.sams.model.Student_StudentCourses;
import cn.java.sams.service.impl.BaseServiceImpl;
import cn.java.sams.service.student.StudentService;
@Service(value="studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService{

	@Autowired
	private StudentDao sd;
	
	@Override
	public Pager<Student> getAllStudentByPage(Student student, Integer page, Integer rows) {
		Pager<Student> pager = sd.getAllStudentByPage(student,page,rows);
		return pager;
	}

	@Override
	public List<String> getClassNum(int id) {
		return sd.getClassNum(id);
	}

	@Override
	public List<Student> getStudentByClassNum(String classNum,Integer majorId) {
		return sd.getStudentByClassNum(classNum,majorId);
	}

}
