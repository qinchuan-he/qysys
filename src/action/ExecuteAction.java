package action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import serviceimpl.Resdataimpl;
import serviceimpl.planImpl;
import util.ExecuteInterface;
import entity.plan;
import entity.responsedetail;

/**
 * 2019-01-30 执行计划使用，内部调用计划内接口
 * @author sean
 *
 */
@Controller
public class ExecuteAction {
	@Resource(name="plan")
	private plan p;
	@Resource(name="responsedetail")
	private responsedetail res;
	@Resource(name="planimpl")
	private planImpl pimp;
	@Resource(name="executeInterface")
	private ExecuteInterface exe;
	@Resource(name="resdataimpl")
	private Resdataimpl resdata;
	@Resource(name="responsedetail")
	private responsedetail respons;
	
	//执行计划
	@ResponseBody
	@RequestMapping(value="/html/planexecute/executePlan.action",method=RequestMethod.GET)
	public Map<String, Object> executePlan(HttpServletRequest req){
		int pid=Integer.parseInt(req.getParameter("id"));
		//判断计划是否在执行
		Map<String, Object> result=pimp.selectId(pid);
		int status=Integer.parseInt(result.get("createid").toString());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);		
		if(status==0){
			//更新计划表为执行状态
			status=1;
			pimp.updateStatus(pid, status);
			//获取环境信息，主要是域名端口
			String sys=pimp.showSystemUrl(pid);		
			//查询出全部接口（注意排序）
			List<Map<String, Object>> list=pimp.showPlanAll(pid);
			if(list.size()==0){
				map.put("status", 0);
				map.put("msg", "空计划无法执行");
				return map;
			}else{
			String sessionId=String.valueOf(new Date().getTime());//设置sessionid
			int maxexe=resdata.maxExenum(pid);//查询执行的次数
			//循环调用接口,调用完成之后存入数据库
			for(Map<String, Object> m:list){
				String url=sys+m.get("url").toString();
				String method=m.get("method").toString();
				String param=m.get("param").toString();
				JSONObject response=exe.httpRequest(url, method, param, sessionId);  //执行接口
				//判断是否执行成功
				String s=response.getString("status");
				int resStatus;
				if(s.equals("1")){
					resStatus=1;
				}else{
					resStatus=0;
				}
				
				respons.setPid(pid);
				respons.setApiid(Integer.parseInt(m.get("inid").toString()));
				respons.setMethod(method);
				respons.setResult(resStatus);
				respons.setResponsedata(response.toString());//json对象转换成字符串
				//respons.setNum(num);
				respons.setExetime(new Date());
				respons.setExenum(maxexe+1);
				String str=resdata.addResponse(respons);//结果存入返回结果表
				
			}
			//更新计划表为执行状态 
			status=0;
			pimp.updateStatus(pid, status);
			map.put("status", 1);	
			map.put("msg", "计划执行完成");
			}
		}else{
			map.put("status", 0);
			map.put("msg", "计划正在执行中");		
		}
		return map;
	
	}
	
	
	//查看计划详情，需要去执行结果表中查询结果,结果分页
	@ResponseBody
	@RequestMapping(value="/html/planexecute/showExecute.action",method=RequestMethod.GET)
	public Map<String, Object> showExecute(HttpServletRequest req){
		int pid=Integer.parseInt(req.getParameter("id"));
		int page = Integer.parseInt(req.getParameter("page"));
		int limit=Integer.parseInt(req.getParameter("limit"));
		int maxexe=resdata.maxExenum(pid);//查询执行的次数
		Map<String, Object> map=new HashMap<String, Object>();
		int count=resdata.countResponse(pid, maxexe);
		map.put("code", 20000);	
		map.put("total", count);
		if(maxexe==0){
			
			map.put("status", 0);
			map.put("msg", "计划还未执行");
		}else{
			
			List<Map<String, Object>> list=resdata.selectPid(pid, maxexe,page-1,limit);			
			map.put("status", 1);
			map.put("list", list);
		}
		return map;
	}
	
	//初始化计划执行状态
	@ResponseBody
	@RequestMapping(value="/html/planexecute/initPlan.action",method=RequestMethod.GET)
	public Map<String, Object> initPlan(HttpServletRequest req){
		int pid=Integer.parseInt(req.getParameter("id"));
		int status=0;//设置为就绪，可执行
		String msg=pimp.updateStatus(pid, status);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 20000);	
		map.put("status", 1);
		map.put("msg", msg);
		return map;
	}
}
