package com.payingguests.model;

public enum Category {
	BOYS("Men"),
	GIRLS("Women"),
	CO("Co-live");

	public String category;

	private Category(String category) {
		this.category = category;
	}
}
