package com.mvp2.sample.shared;

import java.io.Serializable;

public class Vehicle implements Serializable, Comparable<Vehicle> {
	private static final long serialVersionUID = 1L;

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

	@Override
	public int compareTo(Vehicle o) {
		Integer thisId = new Integer(this.id);
		Integer passedId = new Integer(o.getId());
		return thisId.compareTo(passedId);
	}

}