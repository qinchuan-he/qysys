package service;

import java.util.List;
import java.util.Map;

import entity.ininterface;
import entity.inplug;
import entity.plug;

/***
 * 组件方面的接口，涉及增删改查
 * @author sean
 *
 */

public interface plugInter {
	
	public List<plug>  selectPlugAll(); //查询全部组件
	public List<plug> selectPlugAll(int page,int limit);//查询组件列表，分页
	public List<plug> selectPlugName(String name); //根据名字查询组件
	public List<plug> selectPlugName(int page,int limit,String name); //根据名字查询组件
	public List<plug> selectPlugId(int id); //根据id查询组件，修改组件，返回组件信息
	public List<ininterface> selectInterPlug(int id); //根据id查询，查询组件内接口用
	public String addPlug(plug p); //新增组件,暂时新增界面不增加接口
	public String modifyPlug(plug p); //修改组件
	public String deletePlug(int id); //删除组件
	public List<ininterface> selectInter(String name,int id); //组件查询接口用,新增使用
	public List<ininterface> selectInter(String name,int id,int page,int limit); //组件查询接口用,新增使用,分页
	
	public boolean checkExistInter(int pld,int inid);//检查接口是否已存在组建中
	public String addPlugInter(List<inplug> list); //组件添加接口用,传入一组处理好的对象，批量插入
	public String addPlugSingleInter(inplug p);//组件添加接口用,传入单一对象
	public String deletePlugInter(int pid,int inid); //组件删除接口用，单个删除，传入接口id和组件id	 
	
	public int count();//查询总计
	public int count(String name);//名称查询总计
	public int count(String name,int id);//查询总计，不包含在组件内的接口
	public List<Map<String, Object>> selectInterPlugVue(int id); //根据id查询，查询组件内接口用
	
	

}
