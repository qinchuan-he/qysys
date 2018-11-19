package service;
/**
 * @author sean
 * 接口相关，录入接口，增删改查
 */
import java.util.List;
import java.util.Map;

import entity.ininterface;

public interface Inter {

		public List<ininterface> inList();//查询接口，显示全部，不需要参数
		public List<ininterface> selectIdList(int id);//查询接口，通过id查询
		public String addIn(ininterface in);//新增接口，传入一个对象
		public String modifyIn(ininterface in);//修改接口，传入对象
		public String deleteIn(int id);//删除接口，传入id
		public Map<String,Object> selectquery(int id);//根据接口id返回接口环境和接口相关信息,这里只有一行，不用List<Map<String,Object>>，直接一个map搞定
	


}
