package action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Login {
	
	@RequestMapping(value="/jsp/login.action",method=RequestMethod.POST)
	public String execute(HttpServletRequest req,ModelMap model){
		System.out.println("----------------");
		return "main";
	}

}
