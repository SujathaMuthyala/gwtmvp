package com.mvp2.sample.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mvp2.sample.client.service.GreetingService;
import com.mvp2.sample.shared.Contact;
import com.mvp2.sample.shared.ContactDetail;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	private static Map<Integer, Contact> cMap = new HashMap<Integer, Contact>();
	private static Map<String, ContactDetail> cdMap = new HashMap<String, ContactDetail>();
	private static String [] cNames = {"Shirish", "Prasad", "Apurva", "Nikhil", "Vinayak", "Vineeta", "Nivedita", "Sachin", "Partha", "Kapil", "Jyoti"};
	private static String [] sNames = {"Kirtane", "Dixit", "Karnik", "Karode", "Khamkar", "Leekha", "Dixit", "Patil", "Borah", "Gupta", "Sawant"};

	static {
		int i = 0;
		for (int j = 0; j < 20; j++) {
			if((j % 10) == 0) i = 0;
			Contact p = new Contact(j, cNames[i]);
			cMap.put(j, p);
			ContactDetail cd = new ContactDetail(j, cNames[i], sNames[i]);
			cdMap.put("" + j, cd);
			i++;
		}
	}

	@Override
	public List<Contact> getContactList() throws IllegalArgumentException {
		System.out.println("Returning contact list from server");
		List<Contact> list = new ArrayList<Contact>(cMap.values());
		Collections.shuffle(list);
		return list;
	}
	
	@Override
	public ContactDetail getContactDetail(String contactId) throws IllegalArgumentException {
		System.out.println("Returning ContactDeial from server");
		return (ContactDetail)cdMap.get(contactId);
	}

}