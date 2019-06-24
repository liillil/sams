package cn.java.sams.web.filter;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.java.sams.model.Teacher;



public class AuthIncepertor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	 	HttpSession session = request.getSession();
	 	String resource = "";
	 	if(handler instanceof HandlerMethod) {
	 		HandlerMethod handlerMethod = (HandlerMethod) handler; 
	 		Method method = handlerMethod.getMethod();
	 		RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
	 		resource = requestMapping.value()[0];
	 	}else {
	 		throw new RuntimeException("您访问的页面不存在!");
	 	}
	 	
	 	//拿到系统中所有标记要求进行权限控制方法，对应权限控制标记
	  	List<String> allPermissionResource =  (List<String>) request.getServletContext().getAttribute("allPermissionResource");
	 	//拿到目前登录用户所拥有的权限对应的权限控制标记
	 	List<String> userAllPermissionResource =  (List<String>) session.getAttribute("userAllPermissionResource");
	 	//拿到目前登录成功的对象
	 	Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher"); 
	 	if(loginTeacher == null) {
	 		response.sendRedirect(request.getContextPath()+"/login");
	 	}else {
	 		boolean isAdmin = (boolean) session.getAttribute("isAdmin");
	 		if(!isAdmin && allPermissionResource.contains(resource)) {
	 			if(!userAllPermissionResource.contains(resource)) {
	 				throw new RuntimeException("您没有权限访问该功能!");
	 			}
	 		}
	 	}
		return true;
	}

	
	
}
