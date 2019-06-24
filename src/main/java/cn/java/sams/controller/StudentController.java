package cn.java.sams.controller;

import java.util.ArrayList;
import java.util.Date;
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

import cn.java.sams.model.Major;
import cn.java.sams.model.Pager;
import cn.java.sams.model.Student;
import cn.java.sams.model.StudentScore;
import cn.java.sams.service.major.MajorService;
import cn.java.sams.service.student.StudentService;
import cn.java.sams.service.student.Student_StudentCoursesService;
import cn.java.sams.web.AuthClass;
import cn.java.sams.web.AuthMethod;



@AuthClass
@Controller
public class StudentController {
	@Autowired
	private StudentService ss;

	@Autowired
	private MajorService ms;
	@AuthMethod
	@RequestMapping(value = "/studentManager", method = RequestMethod.GET)
	public String studentManager() {
		return "/student_manager";
		
	}
	
	
	
	/**
	 * 获取全部学生信息
	 * @param student
	 * @param page
	 * @param rows
	 * @return
	 */
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="getAllStudentByPage",method=RequestMethod.POST)
	public Pager<Student> getAllStudentByPage(Student student,Integer page,Integer rows) {
		System.out.println(student);
		Pager<Student> pager = ss.getAllStudentByPage(student,page,rows);
		return pager;	
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="student_remove",method=RequestMethod.POST)
	public String studentRemove(@RequestParam(value = "ids[]") int[] ids) {
		try {
			if (ids != null && ids.length > 0) {
				for (int id : ids) {
					ss.delete(id);
				}
			}
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="add_student",method=RequestMethod.POST)
	public String addStudent(@RequestBody Student student) {
		try {
			ss.add(student);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/update_student", method = RequestMethod.POST)
	public String mergeStudent(@RequestBody Student student) {
		try {
			ss.merge(student);
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	
	/**
	 * 获取班级编号
	 * @return
	 */
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/getClassNum", method = RequestMethod.POST)
	public List<Map<String ,String >> getClassNum(@RequestParam(name="id") int id) {
		List<Map<String, String>> list = new ArrayList<>();
		if(id == 0) {
			return list;
		}
		List<String> strs = ss.getClassNum(id);
		int i = 1;
		for(String str:strs) {
			Map<String, String> map = new HashMap<>();
			map.put("id",i+"");
			map.put("text",str+"");
			list.add(map);
			i++;
		}
		return list;
	}
	
}
