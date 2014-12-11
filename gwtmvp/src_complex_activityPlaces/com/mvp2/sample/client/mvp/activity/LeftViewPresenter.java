package com.mvp2.sample.client.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.events.StatusEvent;
import com.mvp2.sample.client.mvp.view.LeftView;
import com.mvp2.sample.client.places.AddressPlace;
import com.mvp2.sample.client.places.AddressesPlace;
import com.mvp2.sample.client.places.ContactPlace;
import com.mvp2.sample.client.places.ContactsPlace;
import com.mvp2.sample.client.places.LeftPlace;
import com.mvp2.sample.client.places.VehiclePlace;
import com.mvp2.sample.client.places.VehiclesPlace;

public class LeftViewPresenter extends AbstractActivity implements Presenter {
	private ClientFactory factory;
	private Place place;

	public LeftViewPresenter(Place place, ClientFactory factory) {
		this.factory = factory;
		this.place = place;
	}

	@Override
	public void goTo(Place place) {
		factory.getPlaceController().goTo(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		factory.getEventBus().fireEvent(new StatusEvent(""));
		LeftView leftView = factory.getLeftView();
		leftView.setPresenter(this);
		leftView.setContactsLabel(factory.getI18Constants().contactsButton());
		leftView.setAddressesLabel(factory.getI18Constants().addressesButton());
		leftView.setVehiclesLabel(factory.getI18Constants().vehiclesButton());

		leftView.getContactsButton().setStyleName("smallButton");
		leftView.getAddressesButton().setStyleName("smallButton");
		leftView.getVehiclesButton().setStyleName("smallButton");

		if (place instanceof LeftPlace) {
		} else if (place instanceof ContactsPlace || place instanceof ContactPlace) {
			leftView.getContactsButton().setStyleName("bigButton");
		} else if (place instanceof AddressesPlace || place instanceof AddressPlace) {
			leftView.getAddressesButton().setStyleName("bigButton");
		} else if (place instanceof VehiclesPlace || place instanceof VehiclePlace) {
			leftView.getVehiclesButton().setStyleName("bigButton");
		}
		panel.setWidget(leftView);
	}

	public void showContacts() {
		goTo(new ContactsPlace(""));
	}

	public void showAddresses() {
		goTo(new AddressesPlace(""));
	}

	public void showVehicles() {
		goTo(new VehiclesPlace(""));
	}
}