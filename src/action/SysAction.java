package action;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.UnsupportedEncodingException;
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
	
	
	
	
	//vue改造
	//进入列表，全局查询,这里进行分页，每页20条
	@ResponseBody
	@RequestMapping(value="/html/system/systemlist.action",method=RequestMethod.GET)
	public Map<String, Object> selectAll1(HttpServletRequest req,ModelMap model){
//		String m=req.getParameter("listQuery");
//		JSONObject json=JSONObject.fromObject(m);
		int page=Integer.valueOf(req.getParameter("page"));
		int limit=Integer.valueOf(req.getParameter("limit"));
		List<sysurl> list=sysInterface.systemList(page-1, limit);
		int count=sysInterface.countId();
		int a=count/limit;
		int b=count%limit;
		int p=0;
		if(b==0){
			p=a;
		}else{
			p=a+1;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("items", list);
		map.put("total", count);
		map.put("code", 20000);
		map.put("status", 1);
		
		System.out.println("进入列表");
		model.addAttribute("syslist", list);
		return map;
	}
	
	//vue改造
	//修改环境信息,post方法请求的是一个json串,这里用get
	@ResponseBody
	@RequestMapping(value="/html/system/systemupdate.action",method=RequestMethod.GET)
	public Map<String, Object> systemUPdate(HttpServletRequest req,ModelMap model){
//	public Map<String, Object> systemUPdate(@RequestBody String req,ModelMap model) throws UnsupportedEncodingException{
		int id=Integer.valueOf(req.getParameter("id"));
		String sysname=req.getParameter("systemname");
//		String sysname=new String(req.getParameter("systemname").getBytes("iso8859-1"),"utf-8");//这个方法不好用，每个参数都要写
				
		String url=req.getParameter("urlname");
		String des=req.getParameter("des");
		System.out.println("-------------------------------"+sysname+"---------"+url+"-------------"+des);
		sys.setId(id);
		sys.setSystemname(sysname);
		sys.setUrlname(url);
		sys.setDes(des);
		sys.setUpdatetime(new Timestamp(new Date().getTime()));
		String res=sysInterface.modifySys(sys);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		if(res.equals("更新成功")){
			map.put("status", 1);
		}else{
			map.put("status", 0);
		}
		return map;
	}
	//vue改造
	//新增，保存成功会跳转进入列表页
	@ResponseBody
	@RequestMapping(value="/html/system/systemadd.action",method=RequestMethod.POST)
	public Map<String, Object> addsys1(@RequestBody String js,ModelMap model){
		Date da=new Date();
		long l=da.getTime();
		Timestamp dt=new Timestamp(l);	
		JSONObject json=JSONObject.fromObject(js);
//		System.out.println("---------------------"+json);
		sys.setSystemname(json.getString("systemname"));
		sys.setUrlname(json.getString("urlname"));
		sys.setDes(json.getString("des"));
		sys.setCreateid(1);
		sys.setCreatetime(dt);
		sys.setUpdatetime(dt);
		String re=sysInterface.addSys(sys);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		if(re.equals("新增完成")){
			map.put("status", 1);
		}else{
			map.put("status", 0);
		}
		return map;
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
	//vue改造
	//删除环境
	@ResponseBody
	@RequestMapping(value="/html/system/systemDelete.action",method=RequestMethod.GET)
	public Map<String, Object> systemDelete(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		String result=sysInterface.deleteSys(id);
//		System.out.println("-----------------------------"+result);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		if(result.equals("删除成功")){
			map.put("status", 1);
		}else{
			map.put("status", 0);
		}
		return map;
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
	
	//vue改造
	//根据名字查询，模糊匹配
	@ResponseBody
	@RequestMapping(value="/html/system/SystemSearch.action",method=RequestMethod.GET)
	public Map<String, Object> systemSearch(HttpServletRequest req,ModelMap model){
		String name=req.getParameter("title");
		int page=Integer.valueOf(req.getParameter("page"));
		int limit=Integer.valueOf(req.getParameter("limit"));
		Map<String, Object> map=new HashMap<String, Object>();
//		System.out.println("--------------------name:"+name);
		int count=0;
		if(name==null||name.equals("")){
//			System.out.println("===============进入为空路线："+name);
			List<sysurl> list=sysInterface.systemList(page-1, limit);
			map.put("data", list);
			count=sysInterface.countId();
		}else{
//			System.out.println("===============进入非空路线："+name);
			List<sysurl> list=sysInterface.selectNameList(name,page-1,limit);
			map.put("data", list);
			count=sysInterface.count(name);
			System.out.println("--------------------------"+count);
		}
		
		map.put("code", 20000);
		map.put("total", count);
		map.put("status", 1);
		return map;
	}
	//vue改造
	//根据id查询
	@ResponseBody
	@RequestMapping(value="/html/system/SystemSearchId.action",method=RequestMethod.GET)
	public Map<String, Object> systemSearchId(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("sysid"));
		List<sysurl> list=sysInterface.selectIdList(id);
		String sysurl1="";
		for(sysurl s:list){
			 sysurl1=s.getUrlname();
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		map.put("sysurl1", sysurl1);
		return map;
	}

}
