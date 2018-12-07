package entity;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 
 * 执行结果明细表,存放每次的执行明细
 * @author sean
 *
 */
@Component(value="responsedetail")
public class responsedetail {
	public int id;//主键
	public int  pid;//计划id
	public int apiid;//接口id
	public String method;//接口请求方式post和get
	public int result;//执行结果是否成1成功，0失败
	public String responsedata;//存放返回数据
	public int num;//接口在计划中的顺序
	public Date exetime;//结果入库的时间，算作执行时间吧
	public int exenum;//计划执行计数，查询结果用
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getApiid() {
		return apiid;
	}
	public void setApiid(int apiid) {
		this.apiid = apiid;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getResponsedata() {
		return responsedata;
	}
	public void setResponsedata(String responsedata) {
		this.responsedata = responsedata;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getExetime() {
		return exetime;
	}
	public void setExetime(Date exetime) {
		this.exetime = exetime;
	}
	public int getExenum() {
		return exenum;
	}
	public void setExenum(int exenum) {
		this.exenum = exenum;
	}
	
}
