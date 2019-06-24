package cn.java.sams.service.teacher.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.sams.dao.student.Student_StudentCoursesDao;
import cn.java.sams.dao.teacher.TeacherDao;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Student;
import cn.java.sams.model.StudentCourses;
import cn.java.sams.model.Student_StudentCourses;
import cn.java.sams.model.Teacher;
import cn.java.sams.model.TeacherCourses;
import cn.java.sams.service.courses.CoursesService;
import cn.java.sams.service.courses.StudentCoursesService;
import cn.java.sams.service.impl.BaseServiceImpl;
import cn.java.sams.service.major.MajorService;
import cn.java.sams.service.student.StudentService;
import cn.java.sams.service.teacher.TeacherService;
import cn.java.sams.util.Encrypt;
import cn.java.sams.util.StringTransferDate;

@Service(value="teacherService")
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService{

	@Autowired
	private TeacherDao td;
	@Autowired
	private MajorService ms;
	@Autowired
	private StudentCoursesService scs;
	@Autowired
	private CoursesService cs;
	@Autowired
	private Student_StudentCoursesDao sscs;
	@Autowired
	private StudentService ss;
	@Override
	public Pager<Teacher> getTeacherByPage(Teacher teacher, Integer page, Integer rows) {
		Pager<Teacher> pager =  td.getTeacherByPage(teacher,page,rows);
		return pager;
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		td.updateTeacher(teacher);
	}

	
	@Override
	public Pager<TeacherCourses> getTeacherCoursesByPage(TeacherCourses teacherCourses, Integer page, Integer rows) {
		Pager<Student_StudentCourses> pager1 = td.getTeacherCoursesByPage(teacherCourses,page,rows);
		List<Student_StudentCourses> sscList = pager1.getRows();
		Pager<TeacherCourses> pager = new Pager<>();
		List<TeacherCourses> tcList = new ArrayList<>();
		for(Student_StudentCourses ssc:sscList) {
			TeacherCourses tc = new TeacherCourses();
			
			tc.setClassNum(ssc.getStudent().getClassNum());
			tc.setCoursesName(ssc.getsCourses().getCourses().getCoursesName());
			tc.setMajor(ms.find(ssc.getStudent().getMajor().getId()));
			tc.setsDate(StringTransferDate.dateTransferString(ssc.getsCourses().getsDate()));
			
			if(ssc.getsCourses().getTeacher() != null) {
				tc.setTeacherName(ssc.getsCourses().getTeacher().getTeacherName());
				tc.setTeacherNum(ssc.getsCourses().getTeacher().getTeacherNum());
			}
			tcList.add(tc);
		}
		pager.setRows(tcList);
		pager.setOffset(pager1.getOffset());
		pager.setSize(pager1.getSize());
		pager.setTotal(pager1.getTotal());
		return pager;
	}

	@Override
	public void addTeacherCoursses(TeacherCourses teacherCourses) {
		
		StudentCourses studentCourses = new StudentCourses();
		studentCourses.setsDate(StringTransferDate.transfer(teacherCourses.getsDate()));
		studentCourses.setCourses(cs.getCoursesByName(teacherCourses.getCoursesName()));
		studentCourses.setTeacher(getTeacherByTeacherNum(teacherCourses.getTeacherNum()));
		
		scs.add(studentCourses);
		
		StudentCourses studentCourses2 = scs.getStudentCourses(teacherCourses.getsDate(),
				teacherCourses.getCoursesName(),
				getTeacherByTeacherNum(teacherCourses.getTeacherNum()));
		List<Student> list = ss.getStudentByClassNum(teacherCourses.getClassNum(),teacherCourses.getMajorId());
		for(Student student : list) {
			Student_StudentCourses student_StudentCourses = new Student_StudentCourses();
			student_StudentCourses.setsCourses(studentCourses2);
			student_StudentCourses.setStudent(student);
			sscs.add(student_StudentCourses);
		}
		//
	}

	@Override
	public void updateTeacherCourses(TeacherCourses teacherCourses) {
		StudentCourses studentCourses = scs.getStudentCourses(teacherCourses.getsDate(),
				teacherCourses.getCoursesName(), null);
		
		 Teacher teacher = td.getTeacherByTeacherNum(teacherCourses.getTeacherNum());
		 
		 studentCourses.setTeacher(teacher);
		 scs.merge(studentCourses);
	}

	@Override
	public Teacher getTeacherByTeacherNum(String teacherNum) {
		return td.getTeacherByTeacherNum(teacherNum);
	}

	@Override
	public Teacher login(String teacherNum, String password) {
		Teacher teacher  = td.getTeacherByTeacherNum(teacherNum);
		if(teacher == null) {
			throw new RuntimeException("用户名或密码有误!");
		}
		if(!teacher.getPassword().equals(Encrypt.md5(teacherNum, password))) {
			throw new RuntimeException("用户名或密码有误!");
		}
		if(teacher.getStatus()!=1) {
			throw new RuntimeException("该用户已被禁用!");
		}
		return teacher;
	}

//	@Override
//	public void deleteTeacherCourses(Map<String,String> id) {
//		String sDate = id.get("sDate");
//		String teacherNum = id.get("teacherNum");
//		String coursesName = id.get("coursesName");
//		
//		
//	}

}
