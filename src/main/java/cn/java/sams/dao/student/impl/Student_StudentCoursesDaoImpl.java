package cn.java.sams.dao.student.impl;

import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Repository;

import cn.java.sams.dao.impl.BaseDaoImpl;
import cn.java.sams.dao.student.Student_StudentCoursesDao;
import cn.java.sams.model.Pager;
import cn.java.sams.model.StudentScore;
import cn.java.sams.model.Student_StudentCourses;
import cn.java.sams.util.StringTransferDate;
@Repository(value="student_StudentCoursesDao")
public class Student_StudentCoursesDaoImpl  extends BaseDaoImpl<Student_StudentCourses> implements Student_StudentCoursesDao{
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Object[]> getStudentScore(StudentScore studentScore, Integer page, Integer rows) {
		Pager<Object[]> pager = new Pager<>();
		String jpql = "select s.sCourses.courses.coursesName,"
					  + "s.student.studentName,"
					  + "s.student.classNum,"
					  + "s.student.studentNum,"
					  + "s.student.major.id,"
					  + "s.score,s.info1,"
					  + "s.info2,s.info3"
					  + " from Student_StudentCourses s where 1=1";
		
		String countJpql  = "select count(s) "
							+ "from Student_StudentCourses s where 1=1";
		String str = "";
		if(studentScore.getCoursesName() != null && studentScore.getCoursesName() != "") {
			str += " and s.sCourses.courses.coursesName like '%"+ studentScore.getCoursesName()+"%'";
		}
		if(studentScore.getClassNum() != null && studentScore.getClassNum() != "") {
			str += " and s.student.classNum like '%"+studentScore.getClassNum()+"%'";
		}
		if(studentScore.getMajorId() != null) {
			str += " and s.student.major ="+studentScore.getMajorId()+"";
		}
		if(studentScore.getStudentNum() != null && studentScore.getStudentNum() != "") {
			str += " and s.student.studentNum like '%"+studentScore.getStudentNum()+"%'";
		}
		if(studentScore.getStudentName() != null && studentScore.getStudentName() != "") {
			str += " and s.student.studentName like '%"+studentScore.getStudentName()+"%'";
		}
		if(studentScore.getsDate() != null) {
			str += " and s.sCourses.sDate >= '"+studentScore.getsDate()+"'";
		}
		if(studentScore.geteDate() != null) {
			str += " and s.sCourses.sDate <= '"+studentScore.geteDate()+"'";
		}
		if(studentScore.getsDate() == null) {
			str += " and s.sCourses.sDate >= '"+StringTransferDate.dateTransferString(new Date())+"'";
		}
		if(studentScore.geteDate() == null) {
			str += " and s.sCourses.sDate <= '"+StringTransferDate.dateTransferString(new Date())+"'";
		}
		jpql = jpql+str+" order by s.student.major.id,s.student.classNum,s.sCourses.courses.coursesName,s.student.studentNum";
		countJpql +=str;
		System.out.println("==========================="+jpql);
		List<Object[]> sScore = em.createQuery(jpql).setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
		long count = (long) em.createQuery(countJpql).getSingleResult();
		int coun = (int) count;
		pager.setRows(sScore);
		pager.setTotal(coun);
		pager.setOffset(page);
		pager.setSize(rows);
		return pager;		
	}
	
	
	
	public void updateStudentScore(StudentScore studentScore) {
		String jpql = "select s from Student_StudentCourses s where 1=1";
		if(studentScore.getCoursesName()!= null && studentScore.getCoursesName()!= "") {
			jpql += " and s.sCourses.courses.coursesName like '%"+studentScore.getCoursesName()+"%'";
		}
		if(studentScore.getStudentNum() != null && studentScore.getStudentNum() != "") {
			jpql += " and s.student.studentNum like '%"+studentScore.getStudentNum()+"%'";
		}
		Student_StudentCourses ssc =  (Student_StudentCourses) em.createQuery(jpql).getSingleResult();
		ssc.setScore(studentScore.getScore());
		ssc.setInfo1(studentScore.getInfo1());
		ssc.setInfo2(studentScore.getInfo2());
		ssc.setInfo2(studentScore.getInfo2());
		merge(ssc);
	}
}
