package com.github.alarit.model;

public class HttpResponse {

	private int code;
	private String body;
	
	public HttpResponse(int code, String body) {
		super();
		this.code = code;
		this.body = body;
	}
	public int getCode() {
		return code;
	}

	public String getBody() {
		return body;
	}
}
