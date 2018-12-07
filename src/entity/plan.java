package entity;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;




@Component("plan")
public class plan {
	public int id;//
	public String pname;//计划名称',
	public String des;//描述',
	public int createid;//创建id'
	public Date createtime;
//	public Timestamp createtime;//创建时间',
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone=" GMT+8")
	public Date updatetime;
//	public Timestamp updatetime;//更新时间
	public int sysid;//环境变量id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getCreateid() {
		return createid;
	}
	public void setCreateid(int createid) {
		this.createid = createid;
	}
	public int getSysid() {
		return sysid;
	}
	public void setSysid(int sysid) {
		this.sysid = sysid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
