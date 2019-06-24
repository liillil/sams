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
import cn.java.sams.model.Major;
import cn.java.sams.model.Pager;
import cn.java.sams.service.major.MajorService;
import cn.java.sams.web.AuthClass;
import cn.java.sams.web.AuthMethod;


@AuthClass
@Controller
public class MajorController {
	
	@Autowired
	private MajorService ms;
	
	/**
	 * 获取专业
	 * @return
	 */
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getAllMajor",method=RequestMethod.POST)
	public List<Map<String,String>> getAllMajor(){
		List<Major> majors = ms.getAllMajor();
		List<Map<String, String>> majorList = new ArrayList<>();
		for(Major major:majors) {
			Map<String, String> majorMap = new HashMap<>();
			majorMap.put("id",major.getId()+"");
			majorMap.put("text",major.getMajorName()+"");
			majorList.add(majorMap);
		}
		return majorList;
	}
	/**
	 * 获取专业课程
	 * @param id
	 * @return
	 */
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getMajorCourses",method=RequestMethod.POST)
	public List<Map<String ,String >> getMajorCourses(@RequestParam(name="id") int id){
		List<Map<String, String>> majorCoursesList = new ArrayList<>();
		if(id == 0) {
			return majorCoursesList;
		}
		Major major = ms.find(id);
		List<Courses> coursess = major.getMajorCourses();		
		for(Courses courses :coursess) {
			Map<String, String> majorCoursesMap = new HashMap<>();
			majorCoursesMap.put("id",courses.getId()+"");
			majorCoursesMap.put("text",courses.getCoursesName()+"");
			majorCoursesList.add(majorCoursesMap);
		}
		return majorCoursesList;
	}
	
	/**
	 * 获取专业信息
	 * @param majorId
	 * @param page
	 * @param rows
	 * @return
	 */
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getMajor",method=RequestMethod.POST)
	public Pager<Major> getMajor(@RequestParam(name="majorId",required=false,defaultValue = "0") Integer majorId,Integer page,Integer rows){
		System.out.println("=============================="+majorId);
		Major major = new Major();
		major.setId(majorId);		
		Pager<Major> pager = ms.getAllMajorByPage(major,page,rows);
		
		return pager;
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/add_major_courses", method = RequestMethod.POST)
	public String addRole(@RequestBody Major major) {
		System.out.println(major.toString());
		try {
			ms.add(major);
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/update_major_courses", method = RequestMethod.POST)
	public String mergeRole(@RequestBody Major major) {
		System.out.println(major.toString());
		try {
			ms.merge(major);
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/remove_major_courses", method = RequestMethod.POST)
	public String removeRole(@RequestParam(value = "ids[]") int[] ids) {

		try {
			if (ids != null && ids.length > 0) {
				for (int id : ids) {
					ms.delete(id);
				}
			}
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	
	
}
