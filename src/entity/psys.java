package entity;

import org.springframework.stereotype.Component;

@Component(value="psys")
public class psys {
	int id;//主键
	int sid;//环境id
	int planid;//计划id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getPlanid() {
		return planid;
	}
	public void setPlanid(int planid) {
		this.planid = planid;
	}
}
