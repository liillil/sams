package cn.java.sams.service.student.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.sams.dao.student.Student_StudentCoursesDao;
import cn.java.sams.model.Major;
import cn.java.sams.model.Pager;
import cn.java.sams.model.StudentScore;
import cn.java.sams.model.Student_StudentCourses;
import cn.java.sams.service.impl.BaseServiceImpl;
import cn.java.sams.service.major.MajorService;
import cn.java.sams.service.student.Student_StudentCoursesService;
@Service(value="student_StudentCoursesService")
public class Student_StudentCoursesServiceImpl  extends BaseServiceImpl<Student_StudentCourses> implements Student_StudentCoursesService{

	@Autowired
	private Student_StudentCoursesDao sscd;
	@Autowired
	private MajorService ms;
	
	@Override
	public Pager<StudentScore> getStudentScore(StudentScore studentScore, Integer page, Integer rows) {
		Pager<Object[]> studentScores = sscd.getStudentScore(studentScore, page,rows);
		List<Object[]> objects= studentScores.getRows();
		List<StudentScore> scores = new ArrayList<>();
		for(Object[] objs :objects) {
			StudentScore score = new StudentScore();
				score.setCoursesName(objs[0].toString()); 
				score.setStudentName(objs[1].toString());
				score.setClassNum(objs[2].toString());
				score.setStudentNum(objs[3].toString());
				score.setMajorId((Integer)objs[4]);
				score.setScore((Integer)objs[5]);
				score.setInfo1((Integer)objs[6]);
				score.setInfo2((Integer)objs[7]);
				score.setInfo3((Integer)objs[8]);
				int majorId = score.getMajorId();
				score.setMajor(ms.find(majorId));
				scores.add(score);
		}
		Pager<StudentScore> pager = new Pager<>();
		pager.setRows(scores);
		pager.setOffset(studentScores.getOffset());
		pager.setSize(studentScores.getSize());
		pager.setTotal(studentScores.getTotal());
		return pager;
	}

	
	
	
	@Override
	public void updateStudentScore(StudentScore studentScore) {
		sscd.updateStudentScore(studentScore);
	}




	@Override
	public Pager<Object[]> getStudentScores(StudentScore studentScore, Integer page, Integer rows) {
		
		return sscd.getStudentScore(studentScore, page, rows);
	}

}
