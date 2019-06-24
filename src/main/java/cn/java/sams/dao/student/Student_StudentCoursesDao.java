package cn.java.sams.dao.student;

import java.util.List;

import cn.java.sams.dao.BaseDao;
import cn.java.sams.model.Pager;
import cn.java.sams.model.StudentScore;
import cn.java.sams.model.Student_StudentCourses;

public interface Student_StudentCoursesDao extends BaseDao<Student_StudentCourses>{
	/**
	 * 查询学生成绩
	 * @param studentScore
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<Object[]> getStudentScore(StudentScore studentScore, Integer page, Integer rows);
	/**
	 * 更新学生成绩
	 * @param studentScore
	 */
	public void updateStudentScore(StudentScore studentScore);
	
	
}
