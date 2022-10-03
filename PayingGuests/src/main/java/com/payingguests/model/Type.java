package com.payingguests.model;

public enum Type {
	AC("AC"),
	NONAC("Non-AC");
	
	public String type;
	
	private Type(String type) {
		this.type=type;
	}
}
