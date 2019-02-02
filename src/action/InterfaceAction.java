package action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import serviceimpl.InInterfaceimpl;
import serviceimpl.SysInterface;
import entity.ininterface;
import entity.sysurl;

/**
 * 接口相关请求
 * @author sean
 *
 */
@Controller
public class InterfaceAction {
	@Resource(name="ininterface")
	private ininterface ininter; //接口实体
	@Resource(name="ininterimpl")
	private InInterfaceimpl interimpl; //接口实现接口
	@Resource(name="sysurl")
	private sysurl sys; //环境实体
	@Resource(name="sysInterface")
	private SysInterface sysinter; //环境实现接口
	
	//全局查询接口，进入列表页，暂时无分页
	@RequestMapping(value="/jsp/interfacelist.action")
	public String interList(HttpServletRequest req,ModelMap model){
		List<ininterface> list=interimpl.inList();
		model.addAttribute("list", list);
		return "interfaceList";
	}
	
	//vue改造查
	//询接口列表，分页
	@ResponseBody
	@RequestMapping(value="/html/interface/InterfaceList.action")
	public Map<String, Object> interfaceList(HttpServletRequest req,ModelMap model){
		int page=Integer.parseInt(req.getParameter("page"));
		int limit=Integer.parseInt(req.getParameter("limit"));
		List<ininterface> list=interimpl.inList(page-1,limit);
		int count=interimpl.cointId();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("total", count);
		map.put("status", 1);
		map.put("code", 20000);
		return map;
	}
	//vue改造，接口名查询
	@ResponseBody
	@RequestMapping(value="/html/interface/InterfaceSelect.action",method=RequestMethod.GET)
	public Map<String, Object> interfaceSelect(HttpServletRequest req,ModelMap model){
		String name=req.getParameter("title");
		int page=Integer.parseInt(req.getParameter("page"));
		int limit=Integer.parseInt(req.getParameter("limit"));
		int count=interimpl.cointId(name);
		Map<String, Object> map=new HashMap<String, Object>();
		List<ininterface> list=interimpl.inSelect(page-1, limit, name);
		map.put("list", list);
		map.put("total", count);
		map.put("code", 20000);
		map.put("status", 1);
		return map;
	}
	//vue改造，新增接口部分
	@ResponseBody
	@RequestMapping(value="/html/interface/InterfaceAdd.action",method=RequestMethod.POST)
	public Map<String, Object> selectAll1(@RequestBody String req,ModelMap model){
		JSONObject json=JSONObject.fromObject(req);
		Timestamp time=new Timestamp(new Date().getTime());
		Map<String, Object> map=new HashMap<String, Object>();
//		String checker=json.getString("checker");
		ininter.setInname(json.getString("inname"));
		ininter.setUrl(json.getString("url"));
		ininter.setMethod(json.getString("method"));
		ininter.setDes(json.getString("des"));
		ininter.setParam(json.getString("param"));
		ininter.setSysid(Integer.parseInt(json.getString("sysid")));
		ininter.setCreatetime(time);
		ininter.setUpdatetime(time);
		ininter.setChecker("");
		String re=interimpl.addIn(ininter);
		map.put("result", re);
		if(re.equals("新增成功")){
			map.put("status", 1);
		}else{
			map.put("status", 0);
		}
		map.put("code", 20000);		
		return map;
	}
	//vue改造，接口详情
	@ResponseBody
	@RequestMapping(value="/html/interface/InterfaceDetails.action",method=RequestMethod.GET)
	public Map<String, Object> interfaceDetails(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		Map<String, Object> result=interimpl.selectquery(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("status", 1);
		map.put("code", 20000);
		map.put("result", result);
		
		return map;
	}
	//vue改造，修改接口
	@ResponseBody
	@RequestMapping(value="/html/interface/InterfaceModify.action",method=RequestMethod.POST)
	public Map<String, Object> interfaceModify(@RequestBody String req,ModelMap model){
		JSONObject json=JSONObject.fromObject(req);
		Timestamp time=new Timestamp(new Date().getTime());
//		String check=json.getString("check");
		ininter.setId(json.getInt("id"));
		ininter.setInname(json.getString("inname"));
		ininter.setUrl(json.getString("url"));
		ininter.setMethod(json.getString("method"));
		ininter.setDes(json.getString("des"));
		ininter.setParam(json.getString("param"));
		ininter.setChecker("");
		ininter.setUpdatetime(time);
		ininter.setSysid(json.getInt("sysid"));		
		String result=interimpl.modifyIn(ininter);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("result", result);
		map.put("ccode", 20000);
		map.put("status", 1);
		return map;
	}
	//vue改造，删除接口
	@ResponseBody
	@RequestMapping(value="/html/interface/InterfaceDelete.action",method=RequestMethod.GET)
	public Map<String, Object> interafaceDelete(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		String result=interimpl.deleteIn(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("result", result);
		map.put("code", 20000);
		map.put("status", 1);
		return map;
	}
	
	
	//查询出环境列表，进入新增页面作为下拉框
	@RequestMapping(value="/jsp/addinterface.action")
	public String selectSys(HttpServletRequest req,ModelMap model){
		List<sysurl> syslist=sysinter.sysList();
		model.addAttribute("syslist", syslist);	
		return "addinterface";
	}
	
	//查询出环境列表，异步请求用，不跳转页面,,被废弃，不能解决选择之后再次触发的问题
	@ResponseBody
	@RequestMapping(value="/jsp/addinterface1.action",method=RequestMethod.POST)
	public List<sysurl> selectSys1(HttpServletRequest req,ModelMap model){
		List<sysurl> syslist=sysinter.sysList();
		
		return syslist;
	}
	
	//新增，本次参数直接传入的字符串，前端也未做处理
	@RequestMapping(value="/jsp/addinter.action",method=RequestMethod.POST)
	public String addinter(HttpServletRequest req, ModelMap model){
		String str=req.getParameter("sysid");
		int i=Integer.parseInt(str);
		Date da=new Date();
		long l=da.getTime();
		Timestamp time=new Timestamp(l);	
		ininter.setInname(req.getParameter("name"));
		ininter.setUrl(req.getParameter("url"));
		ininter.setMethod(req.getParameter("method1"));
		ininter.setDes(req.getParameter("des"));
		ininter.setParam(req.getParameter("param"));
		ininter.setSysid(i);
		ininter.setCreatetime(time);
		ininter.setUpdatetime(time);
		ininter.setChecker(req.getParameter("check"));
		String re=interimpl.addIn(ininter);
		System.out.println(re+req.getParameter("name"));
		return "redirect:/jsp/interfacelist.action";
	}
	
	//测试接口方法，执行接口页面，根据id查询参数
	@RequestMapping(value="/jsp/testinter.action",method=RequestMethod.GET)
	public String TestInterface(HttpServletRequest req,ModelMap model){
		String str=req.getParameter("id");
		int id=Integer.parseInt(str);
		Map<String,Object> map=interimpl.selectquery(id);
		model.addAttribute("map", map);		
//		System.out.println(map);
		return "testRequest";
	}
	
	//根据id查询出接口信息,包含环境信息
	@RequestMapping(value="/jsp/modifyinterselect.action",method=RequestMethod.GET)
	public String selectInterface(HttpServletRequest req,ModelMap model){
		String str=req.getParameter("id");
		int id=Integer.parseInt(str);
		Map<String,Object> map=interimpl.selectquery(id);
		List<sysurl> list=sysinter.selectNotIdList(id);
		model.addAttribute("map", map);	
		model.addAttribute("syslist", list);
		
//		System.out.println(map);
		return "modifyinterface";
	}
	
	//修改接口，传入修改对象,修改成返回列表
	@RequestMapping(value="/jsp/modifyinter.action",method=RequestMethod.POST)
	public String modifyInterface(HttpServletRequest req,ModelMap model){
		Date da=new Date();
		long l=da.getTime();
		Timestamp time=new Timestamp(l);
		String s=req.getParameter("sysid");
		int sid=Integer.parseInt(s);
		String iid=req.getParameter("id");
		int id=Integer.parseInt(iid);		
		ininter.setId(id);
		ininter.setInname(req.getParameter("inname"));
		ininter.setUrl(req.getParameter("url"));
		ininter.setMethod(req.getParameter("method"));
		ininter.setDes(req.getParameter("des"));
		ininter.setParam(req.getParameter("param"));
		ininter.setChecker(req.getParameter("check"));
		ininter.setUpdatetime(time);
		ininter.setSysid(sid);		
		String result=interimpl.modifyIn(ininter);
		System.out.println(result+req.getParameter("inname"));
		return "redirect:/jsp/interfacelist.action";
	}
	
	//删除接口，删除之后返回列表，目前未做是否加入计划和插件组判断
	@RequestMapping(value="/jsp/deleteinter.action",method=RequestMethod.GET)
	public String deleteInter(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		String result=interimpl.deleteIn(id);
		System.out.println(result+id+"------------");
		return "redirect:/jsp/interfacelist.action";
	}
	
	

}
