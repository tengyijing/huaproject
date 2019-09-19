package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * 返回的树形节点数据
 * 必须有id、text、state属性
 * 每次页面请求会把id数据给发送过来
 * @author Administrator
 *
 */
public class EasyUiTreeNode implements Serializable{
	
	private long id;
	private String text;//显示的节点文本
	private String state;//树形结构的状态 closed表示还有子节点，open表示没有子节点
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
