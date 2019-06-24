package cn.java.sams.service.major;


import java.util.List;

import cn.java.sams.model.Courses;
import cn.java.sams.model.Major;
import cn.java.sams.model.Pager;
import cn.java.sams.service.BaseService;

public interface MajorService extends BaseService<Major>{
	/**
	 * 获取全部专业信息
	 * @return
	 */
	public List<Major> getAllMajor();

	/**
	 * 分页获取全部专业信息
	 * @return
	 */
	public Pager<Major> getAllMajorByPage(Major major, Integer page, Integer rows);
}
