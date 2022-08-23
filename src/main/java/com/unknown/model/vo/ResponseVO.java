package com.unknown.model.vo;

import org.springframework.http.HttpStatus;

public class ResponseVO {

	public int httpStatus;

	public String code;

	public String message;

	public Object data;

	public void setFail(String code, String message, Object data) {
		this.httpStatus = HttpStatus.BAD_REQUEST.value();
		this.message = message != null ? message : "fail";
		this.data = data;
	}

	public void setOk(String code, String message, Object data) {
		this.httpStatus = HttpStatus.OK.value();
		this.message = message != null ? message : "success";
		this.data = data;
	}

	public void setError(String code, String message, Object data) {
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
		this.message = message != null ? message : "error";
		this.data = data;
	}
}