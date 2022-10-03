package com.payingguests.model;

public enum Share {
	ONE("Single share"), 
	TWO("Dual share"), 
	THREE("Triple share");

	public String share;

	private Share(String share) {
		this.share = share;
	}
}
