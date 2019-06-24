package cn.java.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.sams.model.Pager;
import cn.java.sams.model.Student;
import cn.java.sams.model.StudentScore;
import cn.java.sams.service.student.Student_StudentCoursesService;
import cn.java.sams.web.AuthClass;
import cn.java.sams.web.AuthMethod;
@AuthClass
@Controller
public class ScoreController {
	
	
	@Autowired
	private Student_StudentCoursesService sscs;
	@AuthMethod
	@RequestMapping(value="/scoreManager",method=RequestMethod.GET)
	public String scoreManager() {
		return "/score_manager";	
	}
	
	
	/**
	 * 获取学生成绩
	 * @param studentScore
	 * @param page
	 * @param rows
	 * @return
	 */
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="getStudentScore",method=RequestMethod.POST)
	public Pager<StudentScore> getStudentScore(StudentScore studentScore,Integer page,Integer rows){
		Pager<StudentScore> pager = sscs.getStudentScore(studentScore, page, rows);
		return pager;
	}
	
	/*@ResponseBody
	@RequestMapping(value="score_remove",method=RequestMethod.POST)
	public String studentRemove(@RequestParam(value = "ids[]") int[] ids) {
		try {
			if (ids != null && ids.length > 0) {
				for (int id : ids) {
					sscs.delete(id);
				}
			}
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}*/
	/*@ResponseBody
	@RequestMapping(value="add_score",method=RequestMethod.POST)
	public String addStudent(@RequestBody StudentScore studentScore) {
		try {
		//	ss.add(student);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}*/
	
	/**
	 * 修改学生成绩
	 * @param studentScore
	 * @return
	 */
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/update_score", method = RequestMethod.POST)
	public String updateStudentScore(@RequestBody StudentScore studentScore) {
		try {
		//	ss.merge(student);
			sscs.updateStudentScore(studentScore);
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	
}
