package com.mvp2.sample.shared;

import java.io.Serializable;

public class Address implements Serializable {

	private int id;
	private String city;
	
	public Address(int id, String city) {
		super();
		this.id = id;
		this.city = city;
	}

	public Address() {
		this(-1, "");
	}

	public int getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + "]";
	}
}