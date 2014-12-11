package com.mvp2.sample.client.factory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.mvp2.sample.client.mvp.ContactsView;
import com.mvp2.sample.client.mvp.LeftView;
import com.mvp2.sample.client.service.GreetingService;
import com.mvp2.sample.client.service.GreetingServiceAsync;

public class ClientFactory {

	private final GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private final EventBus eventBus = new SimpleEventBus();
	private final LeftView leftView = new LeftView();
	private final ContactsView contactsView = new ContactsView();

	public GreetingServiceAsync getGreetService() {
		return rpcService;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public LeftView getLeftView() {
		return leftView;
		// return new LeftView();
	}
	public ContactsView getContactsView() {
		return contactsView;
		// return new ContactsView();
	}
}