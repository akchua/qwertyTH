package com.thesis.monitor.enums;

public enum AccountType {
	
	ADMINISTRATOR("Administrator"),
	
	USER("User"),
	
	BOARD("Board");

	private final String description;
	
	AccountType(final String description) {
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
}
