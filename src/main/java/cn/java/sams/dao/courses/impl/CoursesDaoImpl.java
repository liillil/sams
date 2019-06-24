package cn.java.sams.dao.courses.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.java.sams.dao.courses.CoursesDao;
import cn.java.sams.dao.impl.BaseDaoImpl;
import cn.java.sams.model.Courses;
import cn.java.sams.model.Pager;
@Repository(value="coursesDao")
public class CoursesDaoImpl extends BaseDaoImpl<Courses> implements CoursesDao{

	@Override
	public Pager<Courses> getAllCoursesByPage(Courses courses, Integer page, Integer rows) {
		Pager<Courses> pager = new Pager<>();
		String jpql = "select c from Courses c where 1=1";
		String countJpql = "select count(c) from Courses c where 1=1";
		String str = "";
		if(courses.getCoursesNum() != null && courses.getCoursesNum() != "") {
			str += " and c.CoursesNum like '%"+courses.getCoursesNum()+"%'";
		}
		if(courses.getCoursesName() != null && courses.getCoursesName() != "") {
			str += " and c.coursesName like '%"+courses.getCoursesName()+"%'";
		}
		if(courses.getPeriod() != null) {
			str += " and c.period="+courses.getPeriod()+"";
		}
		if(courses.getCredit() != null) {
			str += " and c.credit="+courses.getCredit()+"";
		}
		jpql += str;
		countJpql += str;
		@SuppressWarnings("unchecked")
		List<Courses> list = em.createQuery(jpql).setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
		long count = (long) em.createQuery(countJpql).getSingleResult();
		int coun = (int) count;
		pager.setRows(list);
		pager.setTotal(coun);
		pager.setOffset(page);
		pager.setSize(rows);
	return pager;
	}

	@Override
	public Courses getCoursesByName(String coursesName) {
		String jpql = "select c from Courses c where c.coursesName='"+coursesName+"'";
		Courses courses = (Courses) em.createQuery(jpql).getSingleResult();
		return courses;
	}

	@Override
	public List<Courses> getAllCourses() {
		String jpql = "from Courses";
		return em.createQuery(jpql).getResultList();
	}

}
