package action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import serviceimpl.SysInterface;
import entity.sysurl;

/**
 * 只处理环境相关请求
 * @author sean
 *
 */
@Controller
public class SysAction {
	@Resource(name="sysInterface")
	private SysInterface sysInterface;
	@Resource(name="sysurl")
	private sysurl sys;
	@RequestMapping(value="/jsp/systemList.action",method=RequestMethod.GET)
	public String selectAll(HttpServletRequest req,ModelMap model){
		System.out.println("进入列表");
		List<sysurl> list=sysInterface.sysList();
		model.addAttribute("syslist", list);
		return "systemList";
	}

}
