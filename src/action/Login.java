package action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import serviceimpl.UserImpl;

@Controller
public class Login {
	@Resource(name="Userimpl")
	public UserImpl userimpl;
	
	@RequestMapping(value="/jsp/login.action",method=RequestMethod.POST)
	public String execute(HttpServletRequest req,ModelMap model){
		System.out.println("----------------");
		return "main";
	}
	
	//登录，vue
	@ResponseBody
	@RequestMapping(value="/html/login/login.action", method=RequestMethod.GET)
	public Map<String, Object> login(HttpServletRequest req,ModelMap model){
		String name=req.getParameter("username");
		String password=req.getParameter("password");
		System.out.println("---------------------------------"+name+"--------"+password);
		int result=userimpl.login(name, password);   //登录成功返回1，失败返回0
		//登录之后要查询权限,用的前端模板需要token设置为admin
		Map<String, Object> map=new HashMap<String, Object>();		
		map.put("token", "admin");		
		Map<String, Object> map2=new HashMap<String, Object>();
		map2.put("roles", "roles");
		map2.put("user", name);		
		map.put("data", map2);
		map.put("code", 20000);
		map.put("status", result);
		System.out.println(map);
		return map;
	}
	
}
