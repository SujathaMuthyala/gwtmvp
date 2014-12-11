package com.mvp2.sample.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp2.sample.shared.Contact;
import com.mvp2.sample.shared.ContactDetail;

public interface GreetingServiceAsync {
	void getContactList(AsyncCallback<List<Contact>> callback);

	void getContactDetail(String id, AsyncCallback<ContactDetail> callback);
}