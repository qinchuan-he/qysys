package entity;

import org.springframework.stereotype.Component;

@Component("inplug")
public class inplug {
	public int id;//
	public int inid;//接口id'
	public int pid;//组件id'
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInid() {
		return inid;
	}
	public void setInid(int inid) {
		this.inid = inid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}

}
