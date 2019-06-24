package cn.java.sams.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.sams.model.Department;
import cn.java.sams.service.department.DepartmentService;
import cn.java.sams.web.AuthClass;
import cn.java.sams.web.AuthMethod;
@AuthClass
@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService ds;
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getAllDepartment",method=RequestMethod.POST)
	public List<Map<String,String>> getAllDepartment(){
		List<Department> departments = ds.getAllDepartment();
		List<Map<String, String>> departmentList = new ArrayList<>();
		for(Department department:departments) {
			Map<String, String> departmentMap = new HashMap<>();
			departmentMap.put("id",department.getId()+"");
			departmentMap.put("text",department.getDepartmentName()+"");
			departmentList.add(departmentMap);
		}
		return departmentList;
	}

}
