package com.cloud.sms.docker.common.entity;


/**
 * 
 * @author zy
 * @Date 2016-06-22 个推发布消息时使用的对象
 */
public class SendMessagesEntity {
	// 发布消息title
	private String title;

	// 发布消息内容
	private String text;

	// 发布消息链接的url
	private String sendUrl;

	// 访问token
	private String token;

	// 是否发送消息开关
	private String isSend;

	// 个推CID
	private String cid;
	// 个推别名
	private String alias;

	// 发送类型
	private String sendType;

	// 请求IP地址
	private String requestIp;

	// 发布服务器地址
	private String server;
	
	// tag
	private String tag;
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
