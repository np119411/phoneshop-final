package com.phoneshop.enums;

public enum ProductType {
	PHONE("PHONE"), PHONECASE("PHONECASE");

	private String code;

	ProductType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
