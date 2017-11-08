package com.sinoauto.dto;

/**
 * 通用Dto
 * @author wuxiao
 * @version 1.0
 * @date 2017-08-16 16:56:04
 */
public class CommonDto {

	// 数据key
	private Integer key;

	// 数据值
	private String value;
	
	private String firstChar;
	
	// 档案管理页面zTree用数据结构
	private Integer id;
	private String name;

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if(id != null) {
			this.id = id;
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null) {
			this.name = name;
		}
		
	}

	public String getFirstChar() {
		return firstChar;
	}

	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}
	
}
