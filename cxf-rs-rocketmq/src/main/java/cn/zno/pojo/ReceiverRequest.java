package cn.zno.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReceiverRequest {

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ReceiverRequest [msg=" + msg + "]";
	}
}
