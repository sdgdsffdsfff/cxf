package cn.zno.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReceiverResponse {

	private String code;

	private String result;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ReceiverResponse [code=" + code + ", result=" + result + "]";
	}

}
