package service;

import java.util.List;

import entity.sysurl;

/**
 * 环境相关接口
 * @author sean
 *
 */
public interface Sys {
	public List<sysurl> sysList();//查询接口，显示全部，不需要参数
	public List<sysurl> selectIdList(int id);//查询接口，通过id查询
	public String addSys(sysurl sys);//新增接口，传入一个对象
	public String modifySys(sysurl sys);//修改接口，传入对象
	public String deleteSys(int id);//删除接口，传入id
	public List<sysurl> selectNameList(String name);//根据名字查询，异步查询
}
