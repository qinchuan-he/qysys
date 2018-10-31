package entity;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component("ininterface")
public class ininterface {
	public int id;//
	public String inname;//接口名称
	public String url;//地址
	public String method;//请求方式
	public String des;//描述
	public String param;//请求参数
	public String checker;//响应断言
	public int createid;//创建id'
	public Timestamp createtime;//创建时间',
	public Timestamp updatetime;//更新时间
	public int sysid;//环境变量id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInname() {
		return inname;
	}
	public void setInname(String inname) {
		this.inname = inname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public int getCreateid() {
		return createid;
	}
	public void setCreateid(int createid) {
		this.createid = createid;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	public int getSysid() {
		return sysid;
	}
	public void setSysid(int sysid) {
		this.sysid = sysid;
	}
}
