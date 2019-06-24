package cn.java.sams.dao.major.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.java.sams.dao.impl.BaseDaoImpl;
import cn.java.sams.dao.major.MajorDao;
import cn.java.sams.model.Major;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Student;
@Repository(value="majorDao")
public class MajorDaoImpl extends BaseDaoImpl<Major> implements MajorDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Major> getAllMajor() {
		String jpql = "from Major";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public Pager<Major> getAllMajorByPage(Major major, Integer page, Integer rows) {
		Pager<Major> pager = new Pager<>();
		String jpql = "select m from Major m where 1=1";
		String countJpql = "select count(m) from Major m where 1=1";
		String str = "";
		if(major.getId() != 0) {
			str += " and m.id="+major.getId();
		}
		jpql = jpql+str;
		countJpql +=str;
		System.out.println("==========================="+jpql);
		List<Major> majors = em.createQuery(jpql).setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
		long count = (long) em.createQuery(countJpql).getSingleResult();
		int coun = (int) count;
		pager.setRows(majors);
		pager.setTotal(coun);
		pager.setOffset(page);
		pager.setSize(rows);
	return pager;
	}

}
