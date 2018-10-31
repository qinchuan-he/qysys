package entity;

import org.springframework.stereotype.Component;

@Component("inplan")
public class inplan {
	public int id;//
	public int inid;//接口id'
	public int pid;//计划id'
	public String result;//执行结果',
	public int num;//计划中排序号
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

}
