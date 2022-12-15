package com.phoneshop.enums;

public enum Role {
	ADMIN("ADMIN"), USER("USER");
	
	private String code;

	Role(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
