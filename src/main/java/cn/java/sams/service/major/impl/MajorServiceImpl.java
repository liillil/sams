package cn.java.sams.service.major.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.sams.dao.major.MajorDao;
import cn.java.sams.model.Courses;
import cn.java.sams.model.Major;
import cn.java.sams.model.Pager;
import cn.java.sams.service.impl.BaseServiceImpl;
import cn.java.sams.service.major.MajorService;
@Service(value="majorService")
public class MajorServiceImpl extends BaseServiceImpl<Major> implements MajorService{
	@Autowired
	private MajorDao md;
	@Override
	public List<Major> getAllMajor() {
		return md.getAllMajor();
	}
	@Override
	public Pager<Major> getAllMajorByPage(Major major, Integer page, Integer rows) {
		
		return md.getAllMajorByPage(major,page,rows);
	}
	
}
