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
import com.mvp2.sample.client.mvp.view.ContactsView;
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
		view = factory.getContactsView();
		view.setPresenter(this);
		view.setBackLabel(factory.getI18Constants().backButton());
		view.setRefreshLabel(factory.getI18Constants().refreshButton());
		view.setReverseLabel(factory.getI18Constants().reverseButton());
		fetchContacts();
		panel.setWidget(view);
	}

	public void fetchContacts() {
		factory.getGreetService().getContactList(new AsyncCallback<List<Contact>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Contact List");
			}

			@Override
			public void onSuccess(List<Contact> result) {
				view.setData(result);
			}
		});
	}

	public void backToHome() {
		goTo(new LeftPlace(parameters));
	}

	public void reverseContactList() {
		factory.getGreetService().getContactList(new AsyncCallback<List<Contact>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Contact List");
			}

			@Override
			public void onSuccess(List<Contact> result) {
				Collections.sort(result);
				Collections.reverse(result);
				view.setData(result);
			}
		});
	}
}