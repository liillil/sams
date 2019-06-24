package cn.java.sams.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.sams.model.Courses;
import cn.java.sams.model.Department;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Teacher;
import cn.java.sams.service.courses.CoursesService;
import cn.java.sams.web.AuthClass;
import cn.java.sams.web.AuthMethod;
@AuthClass
@Controller
public class CoursesController {
	@Autowired
	private CoursesService cs;
	
	@AuthMethod
	@RequestMapping(value="/coursesManager", method = RequestMethod.GET)
	public String coursesManager() {	
		return "/courses_manager";		
	}
	@AuthMethod
	@RequestMapping(value="majorCoursesManager",method=RequestMethod.GET)
	public String majorCoursesManager() {
		return "/major_courses_manager";
		
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getAllCourses",method=RequestMethod.POST)
	public List<Map<String,String>> getAllCourses(){
		List<Courses> coursess = cs.getAllCourses();
		List<Map<String, String>> coursesList = new ArrayList<>();
		for(Courses courses:coursess) {
			Map<String, String> coursesMap = new HashMap<>();
			coursesMap.put("id",courses.getId()+"");
			coursesMap.put("text",courses.getCoursesName()+"");
			coursesList.add(coursesMap);
		}
		return coursesList;
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="getAllCoursesByPage",method=RequestMethod.POST)
	public Pager<Courses> getAllCoursesByPage(Courses courses,Integer page,Integer rows){
		Pager<Courses> pager = cs.getAllCoursesByPage(courses,page,rows);
		return pager;		
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="courses_remove",method=RequestMethod.POST)
	public String coursesRemove(@RequestParam(value = "ids[]") int[] ids) {
		try {
			if (ids != null && ids.length > 0) {
				for (int id : ids) {
					cs.delete(id);
				}
			}
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/add_courses",method=RequestMethod.POST)
	public String addTeacher(@RequestBody Courses courses) {
		try {
			cs.add(courses);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/update_courses", method = RequestMethod.POST)
	public String mergeStudent(@RequestBody Courses courses) {
		try {
			cs.merge(courses);
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	
}
