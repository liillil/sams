package cn.java.sams.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.java.sams.model.Permission;
import cn.java.sams.model.Role;
import cn.java.sams.model.Teacher;
import cn.java.sams.service.teacher.TeacherService;

@Controller
public class LoginController {
	
	@Autowired
	private TeacherService ts;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "/login";
		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String logindo(String teacherNum,String password,HttpSession session) {
		Teacher  loginTeacher = ts.login(teacherNum,password);
		session.setAttribute("loginTeacher", loginTeacher);
		List<Role> roles = loginTeacher.getRoles();
		boolean isAdmin = false;
		List<String> userAllPermissionResource = new ArrayList<>();
		List<Permission> permissions = new ArrayList<>();
		for(Role role:roles) {
			if("超级管理员".equals(role.getRoleName())) {
				isAdmin = true;
				break;//超级管理员，循环没有意义
			}
			//不是超级管理员我们需要把登录成功的用户，关联的所有权限标记取出来
			permissions = role.getPermissions();
			for(Permission permission:permissions) {
				userAllPermissionResource.add(permission.getResource());
			}
		}
		//循环完毕后，userAllPermissionResource包含了登录成功用户所有的权限标记
		session.setAttribute("isAdmin", isAdmin);
		if(!isAdmin) {
			session.setAttribute("userAllPermissionResource", userAllPermissionResource);
		}	
		return "/main";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.removeAttribute("isAdmin");
			session.removeAttribute("loginTeacher");
			List<String> userAllPermissionResource = (List<String>) session.getAttribute("userAllPermissionResource");
			if(userAllPermissionResource != null && userAllPermissionResource.size()>0) {
				session.removeAttribute("userAllPermissionResource");
			}
		}
		session.invalidate();
		return "/login";
	}
}
