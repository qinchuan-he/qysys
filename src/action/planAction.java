package action;
/**
 * @author sean
 * 2018-11-23,测试计划相关action，处理计划相关请求
 * 
 */
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.ininterface;
import entity.plan;
import entity.plandetail;
import entity.plug;
import entity.responsedetail;
import entity.sysurl;
import serviceimpl.InInterfaceimpl;
import serviceimpl.Resdataimpl;
import serviceimpl.SysInterface;
import serviceimpl.planImpl;
import serviceimpl.pluInterImpl;
import util.DateJsonValueProcessor;

@Controller
public class planAction {
	@Resource(name="planimpl")
	private planImpl pimp;
	@Resource(name="plan")
	private plan p;
	@Resource(name="plandetail")
	private plandetail pd;
//	@Resource(name="sysurl")
//	private sysurl sys;
	@Resource(name="sysInterface")
	private SysInterface sysinter;
	@Resource(name="pluginterimpl")
	private pluInterImpl plugimpl;
	@Resource(name="ininterimpl")
	private InInterfaceimpl interimpl; //接口实现接口
	@Resource(name="resdataimpl")
	private Resdataimpl resdata;
	@Resource(name="responsedetail")
	private responsedetail respons;
	
	
	
	@RequestMapping(value="/jsp/planList.action",method=RequestMethod.GET)
	public String planList(HttpServletRequest req,ModelMap model){
		List<plan> list=pimp.list();
		model.addAttribute("list", list);
		return "planList";
	}
	//vue改造,展示计划列表，分页
	@ResponseBody
	@RequestMapping(value="/html/plan/PlanList.action",method=RequestMethod.GET)
	public Map<String, Object> PlanList(HttpServletRequest req){
		int page=Integer.parseInt(req.getParameter("page"));		
		int limit=Integer.parseInt(req.getParameter("limit"));
		List<Map<String, Object>> list=pimp.list(page-1, limit);
		int count=pimp.count();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		map.put("total", count);
		map.put("list", list);
		return map;
	}
	//vue改造，根据计划名查询,分页
	@ResponseBody
	@RequestMapping(value="/html/plan/PlanSearchName.action",method=RequestMethod.GET)
	public Map<String, Object> PlanSearchName(HttpServletRequest req){
		String name=req.getParameter("title");
		if(name==null){
			name="";
		}
		int page=Integer.parseInt(req.getParameter("page"));
		int limit=Integer.parseInt(req.getParameter("limit"));
		List<Map<String, Object>> list=pimp.selectName(name, page-1, limit);
		int count=pimp.count(name);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		map.put("total", count);
		map.put("list", list);
		return map;
	}
	//vue改造，通过id查询计划详情,不包含接口和组件
	@ResponseBody
	@RequestMapping(value="/html/plan/PlanSearchId.action",method=RequestMethod.GET)
	public Map<String, Object> PlanSearchId(HttpServletRequest req){
		int id=Integer.parseInt(req.getParameter("id"));
		Map<String, Object> result=pimp.selectId(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		map.put("list", result);
		return map;
	}
	//vue改造，，通过id查询计划详情,含接口和组件,分页
	@ResponseBody
	@RequestMapping(value="/html/plan/PlanAllSearchId.action",method=RequestMethod.GET)
	public Map<String, Object> PlanAllSearchId(HttpServletRequest req){
		int id=Integer.parseInt(req.getParameter("id"));
		int page=Integer.parseInt(req.getParameter("planpage"));
		int limit=Integer.parseInt(req.getParameter("planlimit"));
		List<Map<String, Object>> list=pimp.planDetail(id,page-1,limit);
		int count=pimp.count(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		map.put("list", list);
		map.put("ptotal", count);
		return map;
	}
	//vue改造,新增计划
	@ResponseBody
	@RequestMapping(value="/html/plan/PlanAdd.action",method=RequestMethod.POST)
	public Map<String, Object> PlanAdd(@RequestBody String req){
		JSONObject json=JSONObject.fromObject(req);
		Date da=new Date();
		p.setCreateid(0);
		p.setPname(json.getString("pname"));
		p.setDes(json.getString("des"));
		p.setSysid(json.getInt("sysid"));
		p.setCreatetime(da);
		p.setUpdatetime(da);
		String str=pimp.addPlan(p);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		map.put("result", str);
		return map;
	}
	//vue改造，修改计划
	@ResponseBody
	@RequestMapping(value="/html/plan/PlanModify.action",method=RequestMethod.POST)
	public Map<String, Object> PlanModify(@RequestBody String req){
		JSONObject json=JSONObject.fromObject(req);
		Date da=new Date();
		p.setId(json.getInt("id"));
		p.setPname(json.getString("pname"));
		p.setDes(json.getString("des"));
		p.setUpdatetime(da);
		p.setSysid(json.getInt("sysid"));
		String str=pimp.modifyPlan(p);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		map.put("result", str);
		return map;
	}
	//vue改造，删除计划
	@ResponseBody
	@RequestMapping(value="/html/plan/PlanDelete.action",method=RequestMethod.GET)
	public Map<String, Object> PlanDelete(HttpServletRequest req){
		int id=Integer.parseInt(req.getParameter("id"));
		String str=pimp.deletePlan(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		map.put("result", str);
		return map;
	}
	//vue改造，计划详情页查询接口，分页,后端判断传入为null
	@ResponseBody
	@RequestMapping(value="/html/plan/planSearahOtherIn.action",method=RequestMethod.GET)
	public Map<String, Object> planSearahOtherIn(HttpServletRequest req){
		int id=Integer.parseInt(req.getParameter("id"));
		String inname=req.getParameter("title");
		int page=Integer.parseInt(req.getParameter("inpage"));
		int limit=Integer.parseInt(req.getParameter("inlimit"));
		if(inname==null){
			inname="";
		}
		List<Map<String, Object>> list=pimp.selectInterName(inname, id,page-1,limit);
		int count=pimp.countInterface(inname, id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		map.put("list", list);
		map.put("intotal", count);
		return map;
	}
	//vue改造，计划详情页查询组件，分页,后端判断传入为null
	@ResponseBody
	@RequestMapping(value="/html/plan/PlanSearchOthaerPlug.action",method=RequestMethod.GET)
	public Map<String, Object> PlanSearchOthaerPlug(HttpServletRequest req){
		int id=Integer.parseInt(req.getParameter("id"));
		String plname=req.getParameter("title");
		int page=Integer.parseInt(req.getParameter("plugpage"));
		int limit=Integer.parseInt(req.getParameter("pluglimit"));
		if(plname==null){
			plname="";
		}
		List<Map<String, Object>> list=pimp.selectPlugName(plname, id,page-1,limit);
		int count=pimp.countPlug(plname, id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		map.put("list", list);
		map.put("pltotal", count);
		return map;
	}
	//vue改造，为计划添加接口和组件，根据type的值来判断
	@ResponseBody
	@RequestMapping(value="/html/plan/PlanAddOther.action",method=RequestMethod.GET)
	public Map<String, Object> PlanAddOther(HttpServletRequest req){
		int pid=Integer.parseInt(req.getParameter("pid"));
		int apiid=Integer.parseInt(req.getParameter("apiid"));
		int type=Integer.parseInt(req.getParameter("type"));
		boolean check=pimp.checkPlan(pid, apiid, type);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		if(check){
			int max=pimp.maxNum(pid)+1;
			pd.setPid(pid);
			pd.setApiid(apiid);
			pd.setType(type);
			pd.setNum(max);
			String str=pimp.plandetailAdd(pd);
			map.put("result", str);
			if(str.equals("新增成功")){
				map.put("status", 1);				
			}else{
				map.put("status", 0);
			}
		}else{
			map.put("status", 0);
		}
		
		return map;
	}
	//vue改造，删除接口和组件，直接删除详情表中的id
	@ResponseBody
	@RequestMapping(value="/html/plan/PlanDeleteOther.action",method=RequestMethod.GET)
	public Map<String, Object> PlanDeleteOther(HttpServletRequest req){
		int id=Integer.parseInt(req.getParameter("id"));
		String str=pimp.deleteDetail(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);
		map.put("status", 1);
		map.put("result", str);
		return map;
	}
	

	
	@RequestMapping(value="/jsp/plandetail.action",method=RequestMethod.GET)
	public String detailPlan(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		Map<String, Object> list=pimp.selectId(id);
		List<Map<String, Object>> detailList=pimp.planDetail(id);
		model.addAttribute("list", list);
		model.addAttribute("detail", detailList);
		model.addAttribute("planid", id);
		return "plandetailList";
	}

	
	

	@RequestMapping(value="/jsp/modifyselect.action",method=RequestMethod.GET)
	public String modifySelectPlan(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		Map<String, Object> list=pimp.selectId(id);
		List<sysurl> syslist=sysinter.selectNotIdList(id);
		model.addAttribute("list", list);
		model.addAttribute("syslist", syslist);
		return "modifyplan";
	}
	
	@ResponseBody
	@RequestMapping(value="/jsp/deletePlan.action",method=RequestMethod.POST)
	public JSONObject deletePlan(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		String str=pimp.deletePlan(id);
		Map<String, String> map=new HashMap<String, String>();
		map.put("result", str);
		JSONObject json=JSONObject.fromObject(map);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/jsp/selectPlanName.action",method=RequestMethod.POST)
	public Map<String, Object> selectPlanName(HttpServletRequest req,ModelMap model){
		String name=req.getParameter("name");
		List<plan> list=pimp.selectName(name);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("status", 1);
		map.put("list", list);
//		JsonConfig config=new JsonConfig();
//		config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
//		JSONArray json=JSONArray.fromObject(list, config);
//		JSONObject json=JSONObject.fromObject(map);
//		JSONArray json=JSONArray.fromObject(list);
		return map;
	}
	
	@RequestMapping(value="/jsp/selectAddPlan.action")
	public String selectAddPlan(HttpServletRequest req,ModelMap model){
		List<sysurl> list=sysinter.sysList();
		model.addAttribute("list", list);
		return "addplan";
	}
	
	@RequestMapping(value="/jsp/addplan.action",method=RequestMethod.POST)
	public String addplan(HttpServletRequest  req,ModelMap model){
		Date da=new Date();
//		long l=da.getTime();
//		Timestamp t=new Timestamp(l);  //类型改成date不需要时间戳了
//		SimpleDateFormat smp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		p.setCreateid(0);
		p.setPname(req.getParameter("pname"));
		p.setDes(req.getParameter("des"));
		p.setSysid(Integer.parseInt(req.getParameter("sysid")));
		p.setCreatetime(da);
		p.setUpdatetime(da);
		String str=pimp.addPlan(p);
		System.out.println(str);
		return "redirect:/jsp/planList.action";
	}
	
	@RequestMapping(value="/jsp/modifyPlan.action",method=RequestMethod.POST)
	public String modifyPlan(HttpServletRequest req,ModelMap model){
		Date da=new Date();
		
		p.setId(Integer.parseInt(req.getParameter("id")));
		p.setPname(req.getParameter("pname"));
		p.setDes(req.getParameter("des"));
		p.setUpdatetime(da);
		p.setSysid(Integer.parseInt(req.getParameter("sysid")));
		String str=pimp.modifyPlan(p);
		System.out.println(str);
		return "redirect:/jsp/planList.action";
	}
	
	@ResponseBody
	@RequestMapping(value="/jsp/selectAddPlug.action",method=RequestMethod.POST)
	public List<plug> selectAddPlug(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		List<plug> list=pimp.selectPlugName(name, id);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="/jsp/selectAddInter.action",method=RequestMethod.POST)
	public List<ininterface> selectAddInter(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		List<ininterface> list=pimp.selectInterName(name, id);	
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="/jsp/showPlan.action",method=RequestMethod.POST)
	public List<Map<String, Object>> showPlan(HttpServletRequest req,ModelMap model){
		int id=Integer.parseInt(req.getParameter("id"));
		List<Map<String, Object>> list=pimp.planDetail(id);
		return list;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/jsp/deletePlanDetail.action",method=RequestMethod.POST)
	public Map<String, String> deletePlanDetail(HttpServletRequest req,ModelMap model){
		int pid=Integer.parseInt(req.getParameter("pid"));
		int id=Integer.parseInt(req.getParameter("id"));
		int type=Integer.parseInt(req.getParameter("type"));
		String str=pimp.deleteDetail(pid, id, type);
		System.out.println(str);
		Map<String, String> map=new HashMap<String, String>();
		map.put("result", str);
		return map;
	}
	//查看接口和组件详情,这里不做数据响应处理，数据响应由返回结果再次调用对应的详情接口
	@ResponseBody
	@RequestMapping(value="/jsp/allDetail.action",method=RequestMethod.POST)
	public Map<String, String> allDetail(HttpServletRequest req,ModelMap model){
//		int id=Integer.parseInt(req.getParameter("id"));
		int type=Integer.parseInt(req.getParameter("type"));
		Map<String, String> map1=new HashMap<String, String>();
		if(type==1){//组件
//			List<plug> plist=plugimpl.selectPlugId(id);
//			List<ininterface> inlist=plugimpl.selectInterPlug(id);
//			model.addAttribute("plug", plist);
//			model.addAttribute("plinlist", inlist);
			map1.put("result", "plug");
			return map1;
		}else if(type==0){//接口
//			Map<String,Object> map=interimpl.selectquery(id);
//			model.addAttribute("map", map);		
////			System.out.println(map);
			map1.put("result", "interface");
			return map1;
		}else{	
			map1.put("result", "error");
			return map1;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/jsp/planAdd.action",method=RequestMethod.POST)
	public Map<String, Integer> planAdd(HttpServletRequest req,ModelMap model){
		int pid=Integer.parseInt(req.getParameter("pid"));
		int id=Integer.parseInt(req.getParameter("id"));
		int type=Integer.parseInt(req.getParameter("type"));
		boolean check=pimp.checkPlan(pid, id, type);
		Map<String, Integer> map=new HashMap<String, Integer>();
		if(check){
			int max=pimp.maxNum(pid)+1;
			pd.setPid(pid);
			pd.setApiid(id);
			pd.setType(type);
			pd.setNum(max);
			String str=pimp.plandetailAdd(pd);
			if(str.equals("新增成功")){
				map.put("result", 1);
			}else{
				map.put("result", 0);
			}
		}else{
			map.put("result", 0);
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/jsp/planExecute.action",method=RequestMethod.POST)
	public Map<String, Object> planExecute(HttpServletRequest req,ModelMap model){
		int pid=Integer.parseInt(req.getParameter("id"));		
		List<Map<String, Object>> detailList=pimp.planDetail(pid);
			if(detailList.size()==0){//空计划
				System.out.println("---------------空计划，无法执行");
				Map<String, Object> mmp=new HashMap<String, Object>();
				mmp.put("status", 0);
				return mmp;
			}else{
				List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
				Map<String, Object> sysmap=pimp.selectId(pid);//获取计划和环境信息
				String sys=(String) sysmap.get("sysurl");
				int maxexe=resdata.maxExenum(pid);
				int i=0;//计数
				for(Map<String, Object> m:detailList){
					int apiid=(int) m.get("id");
					int type=(int) m.get("type");	
//					int num=(int) m.get("num");
					i=i+1;
					List<ininterface> list3=pimp.planexecute(apiid, type);
					for(ininterface face:list3){
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("interid", face.id);
						String url=face.url;
						map.put("num", i);//接口在计划中的顺序
						map.put("url", sys+url);
						map.put("method", face.method);
						map.put("param", face.param);
						list.add(map);
						if(list3.size()>1){
							i=i+1;
						}
					}	
					
				}
				Map<String, Object> mmp=new HashMap<String, Object>();
				mmp.put("list", list);
				mmp.put("status", 1);
				mmp.put("exenum", maxexe+1);
			return mmp;
			}

	}
	
	@RequestMapping(value="/jsp/showresult.action",method=RequestMethod.GET)
	public String selectExecuteDetail(HttpServletRequest req,ModelMap model){
		int pid=Integer.parseInt(req.getParameter("pid"));
		int maxexe=resdata.maxExenum(pid);
		List<Map<String, Object>> list=resdata.selectPid(pid, maxexe);	
		model.addAttribute("list", list);
		return "showResult";
	}
	
	@ResponseBody
	@RequestMapping(value="/jsp/addresponse.action",method=RequestMethod.POST)
	public String addresponse(HttpServletRequest req,ModelMap moodel){
		int pid=Integer.parseInt(req.getParameter("pid"));
		int apiid=Integer.parseInt(req.getParameter("apiid"));
		String method=req.getParameter("method");
		int result=Integer.parseInt(req.getParameter("result"));
		String respon=req.getParameter("respon");
		int num=Integer.parseInt(req.getParameter("num"));
		Date da=new Date();
		int exenum=Integer.parseInt(req.getParameter("exenum"));
		respons.setPid(pid);
		respons.setApiid(apiid);
		respons.setMethod(method);
		respons.setResult(result);
		respons.setResponsedata(respon);
		respons.setNum(num);
		respons.setExetime(da);
		respons.setExenum(exenum);
		String str=resdata.addResponse(respons);
		return str;
	}
	
	
}
