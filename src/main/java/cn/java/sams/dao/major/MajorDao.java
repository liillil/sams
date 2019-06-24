package cn.java.sams.dao.major;

import java.util.List;

import cn.java.sams.dao.BaseDao;
import cn.java.sams.model.Major;
import cn.java.sams.model.Pager;

public interface MajorDao extends BaseDao<Major>{
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
