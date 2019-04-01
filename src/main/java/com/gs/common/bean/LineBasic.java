package com.gs.common.bean;

public class LineBasic {
	
	private String name;
	private double[] data;
	private String[] categories;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double[] getData() {
		return data;
	}
	public void setData(double[] data) {
		this.data = data;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

}
