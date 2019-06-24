package cn.java.sams.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.sams.model.Pager;
import cn.java.sams.model.Teacher;
import cn.java.sams.model.TeacherCourses;
import cn.java.sams.service.teacher.TeacherService;
import cn.java.sams.web.AuthClass;
import cn.java.sams.web.AuthMethod;
@AuthClass
@Controller
public class TeacherCoursesController {
	
	@Autowired
	private TeacherService ts;
	
	
	
	@AuthMethod
	@RequestMapping(value = "/teacherCoursesManager", method = RequestMethod.GET)
	public String teacherCourses() {
		return "/teacher_courses_manager";
		
	} 
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getTeacherCourses",method=RequestMethod.POST)
	public Pager<TeacherCourses> getTeacherCourses(TeacherCourses teacherCourses,Integer page,Integer rows) {
		Pager<TeacherCourses> pager = ts.getTeacherCoursesByPage(teacherCourses,page,rows);
		return pager;
		
	}
	
//	@ResponseBody
//	@RequestMapping(value="teacher_courses_remove",method=RequestMethod.POST)
//	public String studentRemove(@RequestParam(value = "ids") List<Map<String,String>> ids) {
//		
//		try {
//			if (ids != null && ids.size() > 0) {
//				for (Map<String,String> id : ids) {
//					ts.deleteTeacherCourses(id);
//				}
//			}
//		} catch (Exception e) {
//			return "error";
//		}
//		return "ok";
//	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/add_teacher_courses",method=RequestMethod.POST)
	public String addTeacher(@RequestBody TeacherCourses teacherCourses) {
		try {
			ts.addTeacherCoursses(teacherCourses);
			
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/update_teacher_courses", method = RequestMethod.POST)
	public String mergeStudent(@RequestBody TeacherCourses teacherCourses) {
		try {
			ts.updateTeacherCourses(teacherCourses);
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	
}
