package entity;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;
@Component("plug")
public class plug {
	public int id;//
	public String plname;//组件名称
	public String pldes;//描述
	public int createid;//创建id'
	public Timestamp createtime;//创建时间',
	public Timestamp updatetime;//更新时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlname() {
		return plname;
	}
	public void setPlname(String plname) {
		this.plname = plname;
	}
	public String getPldes() {
		return pldes;
	}
	public void setPldes(String pldes) {
		this.pldes = pldes;
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

}
