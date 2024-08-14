package com.qa.gorest.constants;

public enum APIHttpsStatus {

	OK_200(200, "OK"), 
	CREATED_201(201, "Created"), 
	NO_CONTENT_204(204, "No Content"),
	BAD_REQUEST_400(400, "BAD_REQUEST");

	private final Integer code;
	private final String mesg;

	APIHttpsStatus(int code, String mesg) {
		this.code = code;
		this.mesg = mesg;
	}

	public Integer getCode() {
        return code;
	}

	public String getMesg() {
		return mesg;
	}

}
