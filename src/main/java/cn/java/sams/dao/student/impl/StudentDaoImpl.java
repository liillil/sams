package cn.java.sams.dao.student.impl;


import java.security.acl.Group;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.java.sams.dao.impl.BaseDaoImpl;
import cn.java.sams.dao.student.StudentDao;
import cn.java.sams.model.Major;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Student;
import cn.java.sams.model.Student_StudentCourses;
import cn.java.sams.util.StringTransferDate;
@Repository(value="studentDao")
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao{

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Student> getAllStudentByPage(Student student, Integer page, Integer rows) {
		Pager<Student> pager = new Pager<>();
		String jpql = "select s from Student s where 1=1";
		String countJpql = "select count(s) from Student s where 1=1";
		String str = "";
			if(student.getStudentName() != null && student.getStudentName().trim() != "") {
				str += " and s.studentName like '%"+student.getStudentName().trim()+"%'";
			}
			if(student.getStudentNum() != null && student.getStudentNum().trim() != "") {
				str += " and s.studentNum like '%"+student.getStudentNum().trim()+"%'";
			}
			if(student.getStatus() != null) {
				str += " and s.status = "+student.getStatus()+"";
			}
			if (student.getClassNum() != null && student.getClassNum() != "") {
				str += " and s.classNum like '%"+student.getClassNum()+"%'";
			}
			if(student.getMajorId() != null) {
				str += " and s.major="+student.getMajorId();
			}
			if(student.getStartDate() != null) {
				str += " and s.enrollmentDate >= '"+student.getStartDate()+"'";
			}
			if(student.getEndDate() != null) {
				str += "and s.enrollmentDate <= '"+student.getEndDate()+"'";
			}
			if(student.getStartDate() == null) {
				str += " and s.enrollmentDate >= '"+StringTransferDate.dateTransferString(new Date())+"'";
			}
			if(student.getEndDate() == null) {
				str += " and s.enrollmentDate <= '"+StringTransferDate.dateTransferString(new Date())+"'";
			}
			
			jpql = jpql+str;
			countJpql +=str;
			System.out.println("==========================="+jpql);
			List<Student> students = em.createQuery(jpql).setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
			long count = (long) em.createQuery(countJpql).getSingleResult();
			int coun = (int) count;
			pager.setRows(students);
			pager.setTotal(coun);
			pager.setOffset(page);
			pager.setSize(rows);
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getClassNum(int id) {
		String jpql = "select s.classNum from Student s where s.major="+id+" group by s.classNum order by s.classNum";	
		return em.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentByClassNum(String classNum, Integer majorId) {
		String jpql = "select s from Student s where s.classNum='"+classNum+"' and s.major = "+majorId+"";	
		return em.createQuery(jpql).getResultList();
	}

}
