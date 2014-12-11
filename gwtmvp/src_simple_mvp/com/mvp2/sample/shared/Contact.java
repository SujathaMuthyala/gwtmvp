package com.mvp2.sample.shared;

import java.io.Serializable;

public class Contact implements Serializable, Comparable<Contact> {

	private int id;
	private String name;

	public Contact(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Contact() {
		this(-1, "");
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Contact o) {
		Integer thisId = new Integer(this.id);
		Integer passedId = new Integer(o.getId());
		return thisId.compareTo(passedId);
	}
}