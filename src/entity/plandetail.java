package entity;

import org.springframework.stereotype.Component;

@Component(value="plandetail")
public class plandetail {
	int id;//主键
	int pid;//计划id
	int apiid;//接口或组件id
	int type;//状态1为组件0为接口
	int num;//顺序号
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
