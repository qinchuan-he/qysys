package action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.jr.ob.JSON;

import serviceimpl.pluInterImpl;
import entity.ininterface;
import entity.inplug;
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
	@Resource(name="inplug")
	private inplug inpl;
	
	//进入组件页
	@RequestMapping(value="/jsp/plugList.action",method=RequestMethod.GET) //进入界面，查询全局
	public String plugList(HttpServletRequest req,ModelMap model){
		List<plug> list=plugimpl.selectPlugAll();
		model.addAttribute("list", list);
		return "plugList";
	}
	
	//vue改造，查询组件列表
	@ResponseBody
	@RequestMapping(value="/html/plug/PlugList.action",method=RequestMethod.GET)
	public Map<String, Object> plugVueList(HttpServletRequest req,ModelMap model){
		int page=Integer.parseInt(req.getParameter("page"));
		int limit=Integer.parseInt(req.getParameter("limit"));
		List<plug> list=plugimpl.selectPlugAll(page-1,limit);
		int count=plugimpl.count();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("total", count);
		map.put("code", 20000);
		map.put("status", 1);
		return map;
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
	//vue改造，根据名字查询，分页查询
	@ResponseBody
	@RequestMapping(value="/html/plug/PlugSearchName.action",method=RequestMethod.GET)
	public Map<String, Object> plugSearchName(HttpServletRequest req,ModelMap model){
		String name=req.getParameter("title");
		int page=Integer.parseInt(req.getParameter("page"));
		int limit=Integer.parseInt(req.getParameter("limit"));
		List<plug> list=plugimpl.selectPlugName(page-1,limit,name);
		int count=plugimpl.count(name);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("total", count);
		map.put("code", 20000);
		map.put("status", 1);
		return map;
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
	//vue改造，新增组件
	@ResponseBody
	@RequestMapping(value="/html/plug/PlugAdd.action",method=RequestMethod.POST)
	public Map<String, Object> plugAdd(@RequestBody String req){
		JSONObject json=JSONObject.fromObject(req);
		pl.setPlname(json.getString("plname"));
		pl.setPldes(json.getString("pldes"));
		Timestamp time=new Timestamp(new Date().getTime());
		pl.setCreatetime(time);
		pl.setUpdatetime(time);
		String str=plugimpl.addPlug(pl);
		Map<String, Object> map=new HashMap<String, Object>();
		if(str.equals("新增成功")){
			map.put("status", 1);
		}else{
			map.put("status", 0);
		}
		map.put("code", 20000);
		return map;
	}
	
	//进入修改页面,目前还没加上接口的
	@RequestMapping(value="/jsp/modifyselectplug.action",method=RequestMethod.GET)
	public String selectPlugId(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		List<plug> list=plugimpl.selectPlugId(id);
		model.addAttribute("list", list);
		return "modifyPlug";
	}
	//vue改造，根据id查询组件信息
	@ResponseBody
	@RequestMapping(value="/html/plug/PlugSearchId.action",method=RequestMethod.GET)
	public Map<String, Object> plugSearchId(HttpServletRequest req){
		int id=Integer.parseInt(req.getParameter("id"));
		List<plug> list=plugimpl.selectPlugId(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("code", 20000);
		map.put("status", 1);
		return map;
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
	//vue改造，修改组件
	@ResponseBody
	@RequestMapping(value="/html/plug/PlugModify.action",method=RequestMethod.POST)
	public Map<String, Object> plugModify(@RequestBody String req){
		JSONObject json=JSONObject.fromObject(req);
		Timestamp time=new Timestamp(new Date().getTime());
		pl.setId(json.getInt("id"));
		pl.setPlname(json.getString("plname"));
		pl.setPldes(json.getString("plname"));
		pl.setUpdatetime(time);
		String str=plugimpl.modifyPlug(pl);
		Map<String, Object> map=new HashMap<String, Object>();
		if(str.equals("修改成功")){
			map.put("status", 1);
		}else{
			map.put("status", 0);
		}
		map.put("code", 20000);
		return map;
	}
	
	//删除组件，目前未做计划是否包含校验
	@RequestMapping(value="/jsp/deleteplug.action",method=RequestMethod.GET)
	public String deletePlug(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		String str=plugimpl.deletePlug(id);
		System.out.println(str);
		return "redirect:/jsp/plugList.action";
	}
	//vue改造，删除组件
	@ResponseBody
	@RequestMapping(value="/html/plug/PlugDelete.action",method=RequestMethod.GET)
	public Map<String, Object> plugDetele(HttpServletRequest req){
		int id=Integer.parseInt(req.getParameter("id"));
		String str=plugimpl.deletePlug(id);	
		Map<String, Object> map=new HashMap<String, Object>();
		if(str.equals("删除成功")){
			map.put("status", 1);
		}else{
			map.put("status", 0);
		}
		map.put("code", 20000);
		return map;
	}
	
	//查看组件详情，就是看组件下的接口
	@RequestMapping(value="/jsp/describeplug.action",method=RequestMethod.GET)
	public String plugDescribe(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		List<plug> plist=plugimpl.selectPlugId(id);
		List<ininterface> inlist=plugimpl.selectInterPlug(id);
		model.addAttribute("plug", plist);
		model.addAttribute("plinlist", inlist);
		return "plugDescribe";
	}
	//vue改造，获取组件内接口列表，暂不分页，因为接口组件不会包含很多接口
	@ResponseBody
	@RequestMapping(value="/html/plug/PlugSearchIdInterface.action",method=RequestMethod.GET)
	public Map<String, Object> plugSearchIdInterface(HttpServletRequest req){
		int id=Integer.parseInt(req.getParameter("id"));
		List<plug> plist=plugimpl.selectPlugId(id);
		List<ininterface> inlist=plugimpl.selectInterPlug(id);
		String name="",des="";
		for(plug p:plist){
			name=p.getPlname();
			des=p.getPldes();
		}		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("des", des);
		map.put("inlist", inlist);
		map.put("code", 20000);
		map.put("status", 1);
		return map;
	}
	
	//组件内查询，模糊匹配名字，范围全库不包含已关联接口
	@ResponseBody
	@RequestMapping(value="/jsp/searchOtherInter.action",method=RequestMethod.POST)
	public List<ininterface> selectOtherInter(HttpServletRequest req,ModelMap model){
		String name=req.getParameter("name");
		int id=Integer.parseInt(req.getParameter("id"));
		List<ininterface> list=plugimpl.selectInter(name, id);
		return list;
	}
	//vue改造，组件内查询接口,不包含的
	@ResponseBody
	@RequestMapping(value="/html/plug/PlugSelectInter.action",method=RequestMethod.GET)
	public Map<String, Object> selectOtherInterface(HttpServletRequest req){
		String name=req.getParameter("inname");
		int page=Integer.parseInt(req.getParameter("inpage"));
		int limit=Integer.parseInt(req.getParameter("inlimit"));
		if(name==null){
			name="";
		}
		int id=Integer.parseInt(req.getParameter("id"));
//		System.out.println("----------------------------------"+name);
		List<ininterface> list=plugimpl.selectInter(name, id, page-1, limit);
		int count = plugimpl.count(name, id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("inlist", list);
		map.put("intotal", count);
		map.put("code", 20000);
		map.put("status", 1);
		return map;
	}
	
	//查询组件所包含的接口，异步查询，传入id
	@ResponseBody
	@RequestMapping(value="/jsp/searchPlugInter.action",method=RequestMethod.POST)
	public List<ininterface> selectPlugAsy(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		List<ininterface> list=plugimpl.selectInterPlug(id);		
		return list;
	}
	
	//组件增加接口,增加之后还是在查询结果界面
	@ResponseBody
	@RequestMapping(value="/jsp/plugAddInter.action",method=RequestMethod.POST)
	public JSONObject plugAddInter(HttpServletRequest req,ModelMap model){
		int pid=Integer.parseInt(req.getParameter("pid"));
		int inid=Integer.parseInt(req.getParameter("inid"));
		boolean b=plugimpl.checkExistInter(pid, inid);
		if(b){
			inpl.setPid(pid);
			inpl.setInid(inid);
			String str=plugimpl.addPlugSingleInter(inpl);
			System.out.println(str);
			Map<String, String> map=new HashMap<String, String>();
			map.put("result", str);
			JSONObject json=JSONObject.fromObject(map);
			return json;
		}else{
			String str="已经在组件中";
			Map<String, String> map=new HashMap<String, String>();
			map.put("result", str);
			JSONObject json=JSONObject.fromObject(map);
			return json;
		}
	}
	//vue改造，组件增加接口
	@ResponseBody
	@RequestMapping(value="/html/plug/PlugAddInterface.action",method=RequestMethod.GET)
	public Map<String, Object> plugAddInterface(HttpServletRequest req){
		int id=Integer.parseInt(req.getParameter("id"));
		int inid=Integer.parseInt(req.getParameter("inid"));
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		boolean b=plugimpl.checkExistInter(id, inid);
		if(b){
			inpl.setPid(id);
			inpl.setInid(inid);
			String str=plugimpl.addPlugSingleInter(inpl);		
			map.put("result", str);		
		}else{
			String str="已经在组件中";
			map.put("result", str);
		}		
		return map;
	}
	
	//组件删除接口，删除之后还是在原页面
	@ResponseBody
	@RequestMapping(value="/jsp/deletePlugInter.action",method=RequestMethod.POST)
	public JSONObject deletePlugInter(HttpServletRequest req,ModelMap model){
		int pid=Integer.parseInt(req.getParameter("pid"));
		int inid=Integer.parseInt(req.getParameter("inid"));
		String str=plugimpl.deletePlugInter(pid, inid);
		System.out.println(str);
		Map<String, String> map=new HashMap<String, String>();
		map.put("RESULT", str);
		JSONObject json=JSONObject.fromObject(map);
		return json;
	}
	//vue改造，删除组件内接口
	@ResponseBody
	@RequestMapping(value="/html/plug/PlugDeleteInterface.action",method=RequestMethod.GET)
	public Map<String, Object> plugDeleteInterface(HttpServletRequest req){
		int pid=Integer.parseInt(req.getParameter("id"));
		int inid=Integer.parseInt(req.getParameter("inid"));
		String str=plugimpl.deletePlugInter(pid, inid);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("result", str);
		map.put("code", 20000);
		map.put("status", 1);
		return map;
	}

}
