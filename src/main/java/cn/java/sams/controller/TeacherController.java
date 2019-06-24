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
import cn.java.sams.model.Teacher;
import cn.java.sams.service.teacher.TeacherService;
import cn.java.sams.util.Encrypt;
import cn.java.sams.web.AuthClass;
import cn.java.sams.web.AuthMethod;
@AuthClass
@Controller
public class TeacherController {
	
	@Autowired
	private TeacherService ts;
	
	@AuthMethod
	@RequestMapping(value = "/teacherManager", method = RequestMethod.GET)
	public String teacherManager() {
		return "/teacher_manager";		
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getTeacherByPage",method=RequestMethod.POST)
	public Pager<Teacher> getTeacherByPage(Teacher teacher,Integer page,Integer rows){
		Pager<Teacher> pager = ts.getTeacherByPage(teacher,page,rows);
		return pager;		
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="teacher_remove",method=RequestMethod.POST)
	public String studentRemove(@RequestParam(value = "ids[]") int[] ids) {
		try {
			if (ids != null && ids.length > 0) {
				for (int id : ids) {
					ts.delete(id);
				}
			}
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/add_teacher",method=RequestMethod.POST)
	public String addTeacher(@RequestBody Teacher teacher) {
		try {
			String teacherNum = teacher.getTeacherNum();
			String password = teacher.getPassword();
			teacher.setPassword(Encrypt.md5(teacherNum, password));
			ts.add(teacher);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/update_teacher", method = RequestMethod.POST)
	public String mergeStudent(@RequestBody Teacher teacher) {
		try {
			if(teacher.getPassword() == null || teacher.getPassword() == "") {
				Teacher teacher2 = ts.find(teacher.getId());
				teacher.setPassword(teacher2.getPassword());
			}else {
				String teacherNum = teacher.getTeacherNum();
				String password = teacher.getPassword();
				teacher.setPassword(Encrypt.md5(teacherNum, password));
			}
			ts.updateTeacher(teacher);
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	
}
