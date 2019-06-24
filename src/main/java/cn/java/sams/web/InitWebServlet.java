package cn.java.sams.web;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.java.sams.service.role_permission.PermissionService;

public class InitWebServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
//两个初始化:①spring的IOC容器的引用 初始化到InitWebServlet一个静态方法里
//	  ②初始化权限
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void init() throws ServletException {
		//spring的IOC容器的初始化
		ServletContext context = getServletContext();
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		try {	
			//初始化权限
			//packagName 实施权限控制的包全名
			String packagName = "cn.java.sams.controller";
			String packagNamePath = packagName.replace(".","/");
			//拿到packagNamePath，进一步获取到对应的服务器上磁盘上的绝对路径
			String packageNameRealPath = this.getClass().getClassLoader().getResource(packagNamePath).getPath();
		//	System.out.println("=============================="+packageNameRealPath);
			File file = new File(packageNameRealPath);//file就是controller在磁盘的文件夹
			//遍历文件夹里的文件
			String[] classFileNames = file.list(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					if(name.endsWith(".class")) {
						return true;
					}
					return false;
	
				}
			});
			
			List<String> resources = new ArrayList<>();
		
			for(String classFileName : classFileNames) {
				classFileName = classFileName.substring(0,classFileName.indexOf(".class"));
				String classAllPackageName = packagName +"."+classFileName;
				//拿到纯粹的包全名，可以通过他们获取到对应的类的对象，通过反射
				Class clazz = Class.forName(classAllPackageName);
				//拿到controller的对象，获取到他们身上的注解
				if(!clazz.isAnnotationPresent(AuthClass.class)) {
					continue;
				}
				//剩下的都是有@AuthClass类这些都是要进行权限控制的
				//拿到这些类的所有方法
				Method[] methods = clazz.getDeclaredMethods();//包含private方法
				for(Method method : methods) {
					if(!method.isAnnotationPresent(AuthMethod.class)) {
						continue;
					}
					//剩下的都是有@AuthMethod注解的方法，拿到要保存到permission表了resource字段里的值
				//思路一：	String resource = classAllPackageName+"."+method.getName();
					//思路二：
					RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
					resources.add(requestMapping.value()[0]);
				}
			}
			//List<String> resources 包含了controller里所用被@AuthClass和@AuthMethod所作用的
			//方法上面的RequestMapping的value值，都在里面
			PermissionService pService = (PermissionService) applicationContext.getBean(PermissionService.class);
			pService.InitPermissions(resources);
			System.out.println("^^^^^^^^^"+resources);
			context.setAttribute("allPermissionResource", resources);//系统中所有的需要权限控制的方法的权限控制
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
