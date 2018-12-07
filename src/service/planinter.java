package service;

import java.util.List;
import java.util.Map;

import entity.ininterface;
import entity.plan;
import entity.plandetail;
import entity.plug;

/**
 * 测试计划相关方法
 * @author sean
 *
 */
public interface planinter {
	public List<plan> list();//返回所有计划,不包含环境信息
	public Map<String, Object> selectId(int id);//根据id查询计划，显示计划详情包含环境信息
	public List<plan> selectName(String name);//根据name查询计划
	public String addPlan(plan p);//新增计划
	public String modifyPlan(plan p);//修改计划
	public List<Map<String, Object>> planDetail(int id);//查询接口详情，返回接口和组件的信息
	public String deletePlan(int id);//删除计划
	public String deleteDetail(int planid,int apiid,int type);//删除组件或者接口，根据type判断，传入三个id
	public boolean checkPlan(int planid,int apiid,int type);//检查是否已在计划中,别忘了type,1表示组件0表示接口
	public String plandetailAdd(plandetail pd);//增加接口，不区分组件和接口
	
	public List<plug> selectPlugName(String name,int planid);//查询可加入的组件	
	public String addPlug(plandetail p);//新增组件
	
	public List<ininterface> selectInterName(String name,int planid);//查询可加入接口
	public String addInter(plandetail p);//新增接口
	
	public int maxNum(int id);//查询计划明细表的最大num，返回最大值加1
	
	public List<ininterface> planexecute(int apiid,int type);//根据apiid和type查询出所有包含的接口，并且返回，执行计划用

}
