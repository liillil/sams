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
import cn.java.sams.model.Permission;
import cn.java.sams.service.role_permission.PermissionService;
import cn.java.sams.web.AuthClass;
import cn.java.sams.web.AuthMethod;


@AuthClass
@Controller
public class PermissionController {
	@Autowired
	private PermissionService ps;
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getAllPermissions",method=RequestMethod.POST)
	public List<Map<String,String>> getAllPermissions(){
		List<Permission> permissions = ps.getAllPermission();
		List<Map<String, String>> permissionList = new ArrayList<>();
		for(Permission permission:permissions) {
			Map<String, String> permissionMap = new HashMap<>();
			permissionMap.put("id",permission.getId()+"");
			permissionMap.put("text",permission.getResource()+"");
			permissionList.add(permissionMap);
		}
		return permissionList;
	}
	@AuthMethod
	@RequestMapping(value="/getAllPagepermission")
	@ResponseBody
	public Pager<Permission> getAllPagepermission( Integer page, Integer rows) {
		Pager<Permission> pager = ps.getAllPermissionByPage(null, page, rows);
		return pager;
	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/add_permission", method = RequestMethod.POST)
	public String addpermission(@RequestBody Permission permission) {
		System.out.println(permission.toString());
		try {
			ps.add(permission);
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/update_permission", method = RequestMethod.POST)
	public String mergepermission(@RequestBody Permission permission) {
		System.out.println(permission.toString());
		try {
			ps.merge(permission);
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/permission_remove", method = RequestMethod.POST)
	public String removepermission(@RequestParam(value = "ids[]") int[] ids) {

		try {
			if (ids != null && ids.length > 0) {
				for (int id : ids) {
					ps.delete(id);
				}
			}
		} catch (Exception e) {
			return "error";
		}
		return "ok";

	}
	@AuthMethod
	@RequestMapping(value="/permissionManager")
	public String permissionManager() {
		return "/permission_manager";
		
	} 
}
