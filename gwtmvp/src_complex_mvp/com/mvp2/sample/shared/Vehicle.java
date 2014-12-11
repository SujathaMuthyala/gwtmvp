package com.mvp2.sample.shared;

import java.io.Serializable;

public class Vehicle implements Serializable {

	private int id;
	private String model;

	public Vehicle(int id, String model) {
		super();
		this.id = id;
		this.model = model;
	}

	public Vehicle() {
		this(-1, "");
	}

	public int getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", model=" + model + "]";
	}
}