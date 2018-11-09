package action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	//进入列表，全局查询
	@RequestMapping(value="/jsp/systemList.action",method=RequestMethod.GET)
	public String selectAll(HttpServletRequest req,ModelMap model){
		System.out.println("进入列表");
		List<sysurl> list=sysInterface.sysList();
		model.addAttribute("syslist", list);
		return "systemList";
	}
	//新增，保存成功会跳转进入列表页
	@RequestMapping(value="/jsp/addsys.action",method=RequestMethod.POST)
	public String addsys(HttpServletRequest req,ModelMap model){
		Date da=new Date();
		long l=da.getTime();
		Timestamp dt=new Timestamp(l);		
		sys.setSystemname(req.getParameter("sysname"));
		sys.setUrlname(req.getParameter("url"));
		sys.setDes(req.getParameter("des"));
		sys.setCreateid(1);
		sys.setCreatetime(dt);
		sys.setUpdatetime(dt);
		String re=sysInterface.addSys(sys);
		//model.addAttribute("result", re);
		System.out.println(re+req.getParameter("sysname"));
		return "redirect:/jsp/systemList.action";
	}
	//修改计划，先查出来，然后保存
	@RequestMapping(value="/jsp/modify_sys.action",method=RequestMethod.GET)
	public String  selectId(HttpServletRequest req,ModelMap model){
		String a=req.getParameter("id");
		int i=Integer.parseInt(a);
		List<sysurl> list=sysInterface.selectIdList(i);
		model.addAttribute("list", list);
		return "modifySys";
	}
	//保存修改结果
	@RequestMapping(value="/jsp/modifysys.action",method=RequestMethod.POST)
	public String modifysys(HttpServletRequest req,ModelMap model){
		Date da=new Date();
		long l=da.getTime();
		Timestamp dt=new Timestamp(l);	
		String str=req.getParameter("id");
		int id=Integer.parseInt(str);
		sys.setId(id);
		sys.setSystemname(req.getParameter("sysname"));
		sys.setUrlname(req.getParameter("url"));
		sys.setDes(req.getParameter("des"));
		sys.setUpdatetime(dt);
		String result= sysInterface.modifySys(sys);
		System.out.println(result+req.getParameter("sysname"));
		return "redirect:/jsp/systemList.action";
	}
	//删除条目，这里没有用异步请求，使用的重定向;未完，需要加入验证机制，是否有关联接口或计划
	@RequestMapping(value="/jsp/delete_sys.action",method=RequestMethod.GET)
	public String deleteSys(HttpServletRequest req,ModelMap model){
		String str=req.getParameter("id");
		int id=Integer.parseInt(str);
		String result=sysInterface.deleteSys(id);
		System.out.println(result+id);
		//model.addAttribute(result);
		return "redirect:/jsp/systemList.action";
	}
	//根据名字模糊查询,增加注解@ResponseBody，表示响应直接写入body，异步请求
	@ResponseBody
	@RequestMapping(value="/jsp/selectName.action",method=RequestMethod.POST)
	public List<sysurl> selectName(@RequestBody JSONObject name){
		System.out.println("进入环境查询");
//		String n=req.getParameter("name");
		String n=name.getString("name");
//		System.out.println(name);
		System.out.println(n);
		if(n==null||n.equals("")){
			System.out.println("查询环境：为空");
			List<sysurl> list=sysInterface.sysList();
			JSON js=JSONArray.fromObject(list);
			System.out.println(list);
			System.out.println(js);
			return list;
		}else{
			System.out.println("查询环境："+n);
			List<sysurl> list=sysInterface.selectNameList(n);
//			model.addAttribute("syslist", list);
			//return sys;
			return list;	
		}
		
		
	}

}
