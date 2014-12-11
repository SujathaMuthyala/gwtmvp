package com.mvp2.sample.shared;

import java.io.Serializable;

public class ContactDetail implements Serializable, Comparable<ContactDetail> {

	private int id;
	private String fName;
	private String lName;

	public ContactDetail(int id, String fame, String lame) {
		super();
		this.id = id;
		this.fName = fame;
		this.lName = lame;
	}

	public ContactDetail() {
		this(-1, "", "");
	}

	public int getId() {
		return id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	@Override
	public String toString() {
		return "ContactDetail [id=" + id + ", fName=" + fName + ", lName=" + lName + "]";
	}

	@Override
	public int compareTo(ContactDetail o) {
		Integer thisId = new Integer(this.id);
		Integer passedId = new Integer(o.getId());
		return thisId.compareTo(passedId);
	}
}