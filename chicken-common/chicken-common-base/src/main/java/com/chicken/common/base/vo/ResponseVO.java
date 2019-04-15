package com.chicken.common.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 
 * 数据源标识注解
 * 
 * @author huangjianwen
 * @version 1.0
 * @since 2017-04-10
 */
@ApiModel(value = "响应消息基础类")
public class ResponseVO<T> implements Serializable  {

	private static final long serialVersionUID = 1L;

	public static final String SUCCESS = "200";
	public static final String FAIL = "9999";
	
	@ApiModelProperty(value = "响应码", required = true)
	private String code = "200";
	@ApiModelProperty(value = "响应消息", required = true)
	private String message = "success";
	@ApiModelProperty(value = "返回数据", required = false)
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseVO() {
	}

	private ResponseVO(T data) {
		this.data = data;
	}

	public static <T> ResponseVO<T> with(T data) {
		return with(SUCCESS,"success",data);
	}

	public static <T> ResponseVO<T> with(String code,String message,T data) {
		ResponseVO responseVO = new ResponseVO(data);
		responseVO.setCode(code);
		responseVO.setMessage(message);
		responseVO.setData(data);
		return responseVO;
	}

	public static ResponseVO error() {
		return with(FAIL, "内部错误","");
	}
}