package com.mvp2.sample.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mvp2.sample.shared.Contact;
import com.mvp2.sample.shared.ContactDetail;

@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	List<Contact> getContactList() throws IllegalArgumentException;
	ContactDetail getContactDetail(String id) throws IllegalArgumentException;
}