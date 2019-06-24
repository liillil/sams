package cn.java.sams.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.java.sams.model.Pager;
import cn.java.sams.model.Student;
import cn.java.sams.model.StudentCourses;
import cn.java.sams.model.StudentScore;
import cn.java.sams.model.Student_StudentCourses;
import cn.java.sams.model.Teacher;
import cn.java.sams.model.TeacherCourses;
import cn.java.sams.service.courses.StudentCoursesService;
import cn.java.sams.service.major.MajorService;
import cn.java.sams.service.student.StudentService;
import cn.java.sams.service.student.Student_StudentCoursesService;
import cn.java.sams.service.teacher.TeacherService;
import cn.java.sams.util.Encrypt;
import cn.java.sams.util.StringTransferDate;
import cn.java.sams.util.StringUtil;

public class ServiceTest {
	@Test
	public void Test1() {
	//	System.out.println(StringUtil.isNumeric(" "));
		String [] str = StringTransferDate.dateTransferString(new Date()).split("-");
		int i = Integer.parseInt(str[1]);
		Date date1 = null;
		Date date2 = null;
		if(i < 2) {
			String str1 = (Integer.parseInt(str[0])-1)+"-02-01";
			String str2 = Integer.parseInt(str[0]) +"-01-31";
			System.out.println("str1:"+str1 +"   str2:"+str2);
			date1 = StringTransferDate.transfer(str1);
			date2 = StringTransferDate.transfer(str2);
			System.out.println("date1:"+date1+"   date2:"+date2);
		}
	}
	 
	@Test
	public void test() throws ParseException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentService ss = (StudentService) ac.getBean("studentService");
		Student_StudentCoursesService sscs = (Student_StudentCoursesService) ac.getBean("student_StudentCoursesService");
		StudentCoursesService scs = (StudentCoursesService) ac.getBean("studentCoursesService");
//		ScoreService scs = (ScoreService) ac.getBean("scoreService");
//		StudentService ss = (StudentService) ac.getBean("studentService");
//		CoursesService cs = (CoursesService) ac.getBean("coursesService");
		TeacherService ts = (TeacherService) ac.getBean("teacherService");
//		UserService us = (UserService) ac.getBean("userService");
//		TeacherCoursesService tcs = (TeacherCoursesService) ac.getBean("teacherCoursesService");
//		MajorService ms = (MajorService) ac.getBean("majorService");
		MajorService ms = (MajorService) ac.getBean("majorService"); 
//		DepartmentService ds = (DepartmentService) ac.getBean("departmentService");
		//System.out.println(ss.getOneStudent("201530310211").getMojor().getmName());
		//System.out.println(ss.getStudentOne("201530310211", "123456"));
//		Teacher teacher = ts.getOne(1);
//		Major major = ms.getOne(1);
//		for(int i = 1;i < 34; i++) {
//			Student student = new Student();
//			student.setStudentName("a"+i);
//			student.setSex(i%2==0?"男":"女");
//			student.setDateOfBirth(StringTransferDate.transfer("1998-05-13"));
//			String studentNum = "2015303102"+(i<10?"0"+i:i);
//			student.setStudentNum(studentNum);
//			student.setPassword(studentNum);
//			student.setPhone("123456789"+(i<10?"0"+i:i));
//			student.setQq("12345678"+(i<10?"0"+i:i));
//			student.setMajor(major);
//			ss.add(student);
//		}
//		
//		List<Courses> coursess = cs.getClassesResults("1533102",1,10);
//		for(Courses courses:coursess) {
//			System.out.println(courses);
//		}
		
//		List<Score> lists = scs.getClassesResult("1533102");
//		for(Score score: lists) {
//			
//				System.out.println(score);
//			
//		}
		
		//ss.getOneStudent("201530310211");
		
		/*List<MajorCoursesEntity> courseses = scs.getCourses("1533102");
		for(MajorCoursesEntity courses : courseses) {
			System.out.println(courses.getCourses().getcName());
		}*/
		
		
		
//		for(int i = 1;i<5;i++) {
//			Teacher teacher = ts.getOne(i);
//			Teacher teacher = ts.getOne(2);
//			String password = Encrypt.md5Encrypt(teacher.getTeacherNum());
//			teacher.setPassword(password);
//			ts.merge(teacher);
//		}
		
		//System.out.println(Encrypt.md5Encrypt("201530310211"));
		
//		for(int i = 1 ; i<34 ; i++) {
//			for(int j = 1;j<4;j++) {
//				Score  score = new Score();
//				score.setsClass("1533102");
//				int count = (int)(Math.random()*100);
//				score.setScores(count<50?50+(int)(Math.random()*30):count);
//				score.setStudent(ss.getOne(i));
//				score.setCourses(cs.getOne(j));
//				scs.add(score);
//			}
//		}
//		for(int i = 1 ; i<34 ; i++) {
//			Student student = ss.getOne(i);
//			student.setClassesNum("1533102");
//			ss.merge(student);
//		}
		
//		List<Courses>  courses = cs.queryCourseInfo("", 1, 1,null,null);
//		for(Courses c:courses) {
//			System.out.println(c.getcName());
//		}
//		Student student = ss.getOneStudent("201530310211");
//		System.out.println(student.getMajor().getmName());
		//ss.addStudentCourses("通信工程", "计算机原理");
		
//		Teacher teacher = ts.getOne(3);
//		List<Courses> lists= tcs.queryCoursesByTeacher(teacher);
//		for(Courses course:lists) {
//			System.out.println(course);
//		}
		
		
//		List<MajorCoursesEntity> list = tcs.queryTeacherCourses(ts.getOne(1));
//		for(MajorCoursesEntity mc:list) {
//			System.out.println(mc.getCourses().getcName());
//		}
		
//		List<Score[]> list = scs.getClassesResult("1533102");
//		for(Score[] scores: list) {
//			for(Score score : scores) {
//				System.out.println(score.getCourses().getCourses().getcName());
//			}
//			System.out.println("------------------------------");
//		}
		
//		List<MajorCoursesEntity> list = scs.getCourses("1533102");
//		for(MajorCoursesEntity mc : list) {
//			System.out.println(mc.getCourses().getcName());
//		}
		
		//cs.queryCourseInfo(null, 10, 1,null, null);
//		List<Student> students = ss.queryStudentByMajor(ms.getOne(1),"");
//		System.out.println(students);
		
//		Pager<Student> pager =  ss.getAllStudentByPage(new Student(), 1,10);
//		System.out.println(pager.getRows());
		
//		StudentScore studentScore = new StudentScore();
//		studentScore.setClassNum("1533102");
//		studentScore.setCoursesName("通信原理");
//		studentScore.setMajorId(1);	
//		Pager<StudentScore> studentScores = sscs.getStudentScore(new StudentScore(), 1,10);
//		List<StudentScore> scores= studentScores.getRows();
//		System.out.println(scores);
		
		/*Student student = new Student();
		student.setStartDate("2015-09-01");
		
		Pager<Student> pager = ss.getAllStudentByPage(student, 1, 10);
		List<Student> students = pager.getRows();
		System.out.println(students);*/
		/*TeacherCourses tc = new TeacherCourses();
		Pager<TeacherCourses> pager = ts.getTeacherCoursesByPage(tc, 1,10); 
		for(TeacherCourses tcs:pager.getRows()) {
			System.out.println(tcs);
		}
		System.out.println("========================"+pager.getRows().size());
		System.out.println("========================"+pager.getTotal());*/
		
//		List<String> list = ss.getClassNum(1);
//		System.out.println(list);
		
//		Teacher teacher = ts.find(6);
//		String password = teacher.getPassword();
//		teacher.setPassword(Encrypt.md5(teacher.getTeacherNum(),"123"));
//		ts.merge(teacher);]
		
		for(int i = 37;i<=72;i++) {
			Student student = ss.find(i);
			student.setEnrollmentDate(StringTransferDate.transfer("2015-09-01"));
			ss.merge(student);
		}
		
	}
	@Test
	public void test4() {
		String string="0263F2D6CF9BD46D4D58DEF6DFF24AFEdlscssh51515814689029https%3A%2F%2Ftestpay.wwmj100.com%2Fpay%2FexhfnotifyURL%2Fgyzjgame20190410151815044b00ada6ce84c1176ac8d0925268439";
		
		System.out.println(Encrypt.md5(string));
	}
	
}
