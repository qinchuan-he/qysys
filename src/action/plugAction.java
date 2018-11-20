package action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import serviceimpl.pluInterImpl;
import entity.ininterface;
import entity.plug;

/**
 * 组件的处理类,包含组件的增删改查和添加接口，目前接口未做键值对处理，统一都是json发送
 * @author sean
 *2018-11-20
 */
@Controller
public class plugAction {
	@Resource(name="plug")
	private plug pl;
	@Resource(name="pluginterimpl")
	private pluInterImpl plugimpl;
	
	//进入组件页
	@RequestMapping(value="/jsp/plugList.action",method=RequestMethod.GET) //进入界面，查询全局
	public String plugList(HttpServletRequest req,ModelMap model){
		List<plug> list=plugimpl.selectPlugAll();
		model.addAttribute("list", list);
		return "plugList";
	}
	
	//界面查询，异步;不需要ModelMap，直接返回list就行
	@ResponseBody
	@RequestMapping(value="/jsp/searchPlugName.action",method=RequestMethod.GET)
	public List<plug> selectPlugName(HttpServletRequest req,ModelMap model){
		String sname=req.getParameter("name");
		System.out.println(sname);
		List<plug> list=plugimpl.selectPlugName(sname);
		return list;
	}
	
	//增加组件
	@RequestMapping(value="/jsp/addPlug.action",method=RequestMethod.POST)
	public String addPlug(HttpServletRequest req,ModelMap model){
		pl.setPlname(req.getParameter("plname"));
		pl.setPldes(req.getParameter("pldes"));
		Timestamp time=new Timestamp(new Date().getTime());
		pl.setCreatetime(time);
		pl.setUpdatetime(time);
		String str=plugimpl.addPlug(pl);
		System.out.println(str+"--------"+req.getParameter("plname"));
		return "redirect:/jsp/plugList.action";
	}
	
	//进入修改页面,目前还没加上接口的
	@RequestMapping(value="/jsp/modifyselectplug.action",method=RequestMethod.GET)
	public String selectPlugId(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		List<plug> list=plugimpl.selectPlugId(id);
		model.addAttribute("list", list);
		return "modifyPlug";
	}
	//提交组件修改，暂未加接口
	@RequestMapping(value="/jsp/modifyPlug.action",method=RequestMethod.POST)
	public String modifyPlug(HttpServletRequest req,ModelMap model){
		Timestamp time=new Timestamp(new Date().getTime());
		pl.setId(Integer.parseInt(req.getParameter("id")));
		pl.setPlname(req.getParameter("plname"));
		pl.setPldes(req.getParameter("pldes"));
		pl.setUpdatetime(time);
		String str=plugimpl.modifyPlug(pl);
		System.out.println(str+"-------------");
		return "redirect:/jsp/plugList.action";
	}
	
	//删除组件，目前未做计划是否包含校验
	@RequestMapping(value="/jsp/deleteplug.action",method=RequestMethod.GET)
	public String deletePlug(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		String str=plugimpl.deletePlug(id);
		System.out.println(str);
		return "redirect:/jsp/plugList.action";
	}
	
	//查看组件详情，就是看组件下的接口
	
	public String plugDescribe(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		List<plug> plist=plugimpl.selectPlugId(id);
		List<ininterface> inlist=plugimpl.selectInterPlug(id);
		model.addAttribute("plug", plist);
		model.addAttribute("plinlist", inlist);
		return "plugDescribe";
	}

}
