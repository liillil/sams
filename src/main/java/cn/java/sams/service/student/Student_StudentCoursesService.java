package cn.java.sams.service.student;


import cn.java.sams.model.Pager;
import cn.java.sams.model.StudentScore;
import cn.java.sams.model.Student_StudentCourses;
import cn.java.sams.service.BaseService;

public interface Student_StudentCoursesService extends BaseService<Student_StudentCourses>{
	/**
	 * 查询学生成绩
	 * @param studentScore
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<StudentScore> getStudentScore(StudentScore studentScore, Integer page, Integer rows);
	/**
	 * 更新学生成绩
	 * @param studentScore
	 */
	public void updateStudentScore(StudentScore studentScore);
	
	public Pager<Object[]> getStudentScores(StudentScore studentScore, Integer page, Integer rows);
}
