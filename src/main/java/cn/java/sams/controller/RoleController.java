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

import cn.java.sams.model.Pager;
import cn.java.sams.model.Role;
import cn.java.sams.service.role_permission.RoleService;
import cn.java.sams.web.AuthClass;
import cn.java.sams.web.AuthMethod;

@AuthClass
@Controller
public class RoleController {

	@Autowired
	private RoleService rs;
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="getAllRoles",method=RequestMethod.POST)
	public List<Map<String, String >> getAllRoles () {
		List<Role> roles = rs.getAllRole();
		List<Map<String, String>> roleList = new ArrayList<>();
		for(Role role:roles) {
			Map<String, String> roleMap = new HashMap<>();
			roleMap.put("id",role.getId()+"");
			roleMap.put("text",role.getRoleName()+"");
			roleList.add(roleMap);
		}
		return roleList;
	}
	
	/**
	 * 	分页查询全部角色信息
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 */
	@AuthMethod
	@RequestMapping(value="/getAllPageRole")
	@ResponseBody
	public Pager<Role> getAllPageRole( Integer page, Integer rows) {
		Pager<Role> pager = rs.getRoleByPage(null, page, rows);
		return pager;
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/add_role", method = RequestMethod.POST)
	public String addRole(@RequestBody Role role) {
		System.out.println(role.toString());
		try {
			rs.add(role);
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/update_role", method = RequestMethod.POST)
	public String mergeRole(@RequestBody Role role) {
		System.out.println(role.toString());
		try {
			rs.merge(role);
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/role_remove", method = RequestMethod.POST)
	public String removeRole(@RequestParam(value = "ids[]") int[] ids) {

		try {
			if (ids != null && ids.length > 0) {
				for (int id : ids) {
					rs.delete(id);
				}
			}
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	@AuthMethod
	@RequestMapping(value="/roleManager")
	public String roleManager() {
		return "/role_manager";
		
	} 
}
