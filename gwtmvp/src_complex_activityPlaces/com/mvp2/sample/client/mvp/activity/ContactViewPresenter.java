package com.mvp2.sample.client.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.events.StatusEvent;
import com.mvp2.sample.client.mvp.view.ContactView;
import com.mvp2.sample.client.places.ContactPlace;
import com.mvp2.sample.client.places.ContactsPlace;
import com.mvp2.sample.shared.Contact;

public class ContactViewPresenter extends AbstractActivity implements Presenter {

	private ClientFactory factory;
	private String parameters;
	private ContactView view;

	public ContactViewPresenter(ContactPlace place, ClientFactory factory) {
		this.factory = factory;
		this.parameters = place.getParameters();
	}

	@Override
	public void goTo(Place place) {
		factory.getPlaceController().goTo(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = factory.getContactView();
		view.setPresenter(this);
		view.setBackLabel(factory.getI18Constants().backButton());
		view.setContactDetailLabel(factory.getI18Constants().contactDetail());
		view.setContactIdLabel(factory.getI18Constants().contactID());
		view.setContactCityLabel(factory.getI18Constants().contactName());
		panel.setWidget(view);
		int contactId = -1;
		try {
			contactId = Integer.parseInt(parameters);
		} catch(Exception ex) {
		}
		factory.getEventBus().fireEvent(new StatusEvent("Loading Address Details for contact id " + contactId));
		getContactDetails(contactId);
	}

	private void getContactDetails(final int id) {
		factory.getGreetService().getContactDetails(id, new AsyncCallback<Contact>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Contact Details");
				factory.getEventBus().fireEvent(new StatusEvent("Error fetching Contact Details for contact id " + id));
			}

			@Override
			public void onSuccess(Contact result) {
				String entityId = "";
				String entityValue = "";
				if (result != null) {
					entityId = "" + result.getId();
					entityValue = result.getName();
				}
				view.setContactIdValue(entityId);
				view.setContactCityValue(entityValue);
				factory.getEventBus().fireEvent(new StatusEvent("Contact Details for " + entityValue + "(" + entityId + ") loaded."));
			}
		});
	}

	public void backToContactList() {
		goTo(new ContactsPlace("refresh"));
	}
}