package service;

import java.util.List;
import java.util.Map;

import entity.responsedetail;

/**
 * 
 * 返回结果明细表接口
 * @author sean
 *
 */
public interface resdata {
	public int maxExenum(int pid);//根据计划id返回最后一次执行的顺序号,注意为空判断
	public List<Map<String, Object>> selectPid(int pid,int exenum);//返回执行明细结果，需要输出接口名字，不是一张表
	public String addResponse(responsedetail res);//新增一条执行明细
	

}
