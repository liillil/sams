package cn.java.sams.dao.teacher.impl;

import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Repository;

import cn.java.sams.dao.impl.BaseDaoImpl;
import cn.java.sams.dao.teacher.TeacherDao;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Student_StudentCourses;
import cn.java.sams.model.Teacher;
import cn.java.sams.model.TeacherCourses;
import cn.java.sams.util.StringTransferDate;
@Repository(value="teacherDao")
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao{

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Teacher> getTeacherByPage(Teacher teacher, Integer page, Integer rows) {
		String jpql = "select t from Teacher t where 1=1";
		String countJpql = "select count(t) from Teacher t where 1=1";
		String str = "";
		if(teacher.getTeacherNum() != null && teacher.getTeacherNum() != "") {
			str += " and t.teacherNum like '%"+teacher.getTeacherNum()+"%'";
		}
		if(teacher.getTeacherName() != null && teacher.getTeacherName() != "") {
			str += " and t.teacherName like '%"+teacher.getTeacherName()+"%'";
		}
		if(teacher.getStatus() != null) {
			str += " and t.status = "+teacher.getStatus()+"";
		}
		
		if(teacher.getStartDate() != null && teacher.getStartDate() != "") {
			str += " and t.dateOfEnrollment >= '"+teacher.getStartDate()+"'";
		}
		if(teacher.getEndDate() != null && teacher.getEndDate() != "") {
			str += " and t.dateOfEnrollment <= '"+teacher.getEndDate()+"'";
		}
		if(teacher.getStartDate() == null) {
			str += " and t.dateOfEnrollment >= '"+StringTransferDate.dateTransferString(new Date())+"'";
		}
		if(teacher.getEndDate() == null) {
			str += " and t.dateOfEnrollment <= '"+StringTransferDate.dateTransferString(new Date())+"'";
		}
		jpql += str;
		countJpql += str;
		Pager<Teacher> pager = new Pager<>();
		List<Teacher> teachers = em.createQuery(jpql).setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
		long count = (long) em.createQuery(countJpql).getSingleResult();
		int coun = (int) count;
		pager.setRows(teachers);
		pager.setTotal(coun);
		pager.setOffset(page);
		pager.setSize(rows);
		return pager;
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		int id = teacher.getId();
		Teacher teacher2 = find(id);
		if(teacher.getPassword() == null || teacher.getPassword() == "") {
			teacher.setPassword(teacher2.getPassword());
		}
		merge(teacher);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Student_StudentCourses> getTeacherCoursesByPage(TeacherCourses teacherCourses, Integer page, Integer rows) {
		Pager<Student_StudentCourses> pager = new Pager<>();
		String jpql = "select s from Student_StudentCourses s where 1=1";
		String countJpql = "select s from Student_StudentCourses s where 1=1";
		String str = "";
		if(teacherCourses.getsDate() != null && teacherCourses.getsDate() != "") {
			str += " and s.sCourses.sDate >='"+teacherCourses.getsDate()+"'";
		}else {
			str += " and s.sCourses.sDate >='"+StringTransferDate.dateTransferString(new Date())+"'";
		}
		
		if(teacherCourses.getCoursesName() != null && teacherCourses.getCoursesName() != "") {
			str += " and s.sCourses.courses.coursesName like '%"+teacherCourses.getCoursesName()+"%'";
		}
		if(teacherCourses.getTeacherName() != null && teacherCourses.getTeacherName() != "") {
			str += " and s.sCourses.teacher.teacherName like '%"+teacherCourses.getTeacherName()+"%'";
		}
		if(teacherCourses.getTeacherNum() != null && teacherCourses.getTeacherNum() != "") {
			str += " and s.sCourses.teacher.teacherNum like '%"+teacherCourses.getTeacherNum()+"%'";
		}
		if(teacherCourses.getMajorId() != null) {
			str += " and s.student.major ="+teacherCourses.getMajorId()+"";
		}
		if(teacherCourses.getClassNum() != null && teacherCourses.getClassNum() != "") {
			str += " and s.student.classNum = '"+teacherCourses.getClassNum()+"'";
		}
		jpql = jpql+str+" group by s.student.major.id,s.student.classNum,s.sCourses";
		countJpql =countJpql+str+" group by s.student.major.id,s.student.classNum,s.sCourses";
		List<Student_StudentCourses> ssc = em.createQuery(jpql).setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
		//List<Student_StudentCourses> ssc = em.createQuery(jpql).setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
		List<Student_StudentCourses> ssclist =  em.createQuery(countJpql).getResultList();
		int coun = ssclist.size();
		pager.setRows(ssc);
		pager.setTotal(coun);
		pager.setOffset(page);
		pager.setSize(rows);
		return pager;		
	}

	@Override
	public Teacher getTeacherByTeacherNum(String teacherNum) {
		String jpql = "select t from Teacher t where t.teacherNum = '"+teacherNum+"'";
		Teacher teacher = (Teacher) em.createQuery(jpql).getSingleResult();
		return teacher;
	}

}
