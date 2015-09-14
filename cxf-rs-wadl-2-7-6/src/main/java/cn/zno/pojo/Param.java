package cn.zno.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Param {
	
	private int wordNumber;
	
	private String name;

	public int getWordNumber() {
		return wordNumber;
	}

	public void setWordNumber(int wordNumber) {
		this.wordNumber = wordNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
