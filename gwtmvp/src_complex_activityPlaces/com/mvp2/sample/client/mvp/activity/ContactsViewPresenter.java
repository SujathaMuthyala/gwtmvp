package com.mvp2.sample.client.mvp.activity;

import java.util.Collections;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.events.StatusEvent;
import com.mvp2.sample.client.mvp.view.ContactsView;
import com.mvp2.sample.client.places.ContactPlace;
import com.mvp2.sample.client.places.ContactsPlace;
import com.mvp2.sample.client.places.LeftPlace;
import com.mvp2.sample.shared.Contact;

public class ContactsViewPresenter extends AbstractActivity implements Presenter {

	private ClientFactory factory;
	private String parameters;
	private ContactsView view;

	public ContactsViewPresenter(ContactsPlace place, ClientFactory factory) {
		this.factory = factory;
		this.parameters = place.getParameters();
	}

	@Override
	public void goTo(Place place) {
		factory.getPlaceController().goTo(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		factory.getEventBus().fireEvent(new StatusEvent("Loading Contact List"));
		view = factory.getContactsView();
		view.setVisible(false);
		view.setPresenter(this);
		view.setBackLabel(factory.getI18Constants().backButton());
		view.setRefreshLabel(factory.getI18Constants().refreshButton());
		view.setReverseLabel(factory.getI18Constants().reverseButton());
		panel.setWidget(view);
		if(parameters != null) {
			if(parameters.trim().equalsIgnoreCase("reverse")) {
				getReverseContacts();
			} else {
				getAllContacts();
			}
		}
	}
	public void fetchContacts() {
		goTo(new ContactsPlace("refresh"));
	}

	private void getAllContacts() {
		factory.getGreetService().getContactList(new AsyncCallback<List<Contact>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Contact List");
				factory.getEventBus().fireEvent(new StatusEvent("Error loading Contact List"));
			}

			@Override
			public void onSuccess(List<Contact> result) {
				view.setData(result);
				view.setVisible(true);
				factory.getEventBus().fireEvent(new StatusEvent("Loaded Contact List successfully"));
			}
		});
	}

	public void backToHome() {
		goTo(new LeftPlace(""));
	}
	
	public void showContact(int id) {
		goTo(new ContactPlace("" + id));
	}

	public void reverseContactList() {
		goTo(new ContactsPlace("reverse"));
	}

	private void getReverseContacts() {
		factory.getGreetService().getContactList(new AsyncCallback<List<Contact>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Contact List");
				factory.getEventBus().fireEvent(new StatusEvent("Error loading Reverse Contact  List"));
			}

			@Override
			public void onSuccess(List<Contact> result) {
				Collections.sort(result);
				Collections.reverse(result);
				view.setData(result);
				view.setVisible(true);
				factory.getEventBus().fireEvent(new StatusEvent("Loaded Reverse Contact List"));
			}
		});
	}
}