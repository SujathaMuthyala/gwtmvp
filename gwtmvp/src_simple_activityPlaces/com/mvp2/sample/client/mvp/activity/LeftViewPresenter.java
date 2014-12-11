package com.mvp2.sample.client.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.view.LeftView;
import com.mvp2.sample.client.places.AddressesPlace;
import com.mvp2.sample.client.places.ContactsPlace;
import com.mvp2.sample.client.places.LeftPlace;
import com.mvp2.sample.client.places.VehiclesPlace;

public class LeftViewPresenter extends AbstractActivity implements Presenter {
	private ClientFactory factory;
	private String parameters;

	public LeftViewPresenter(LeftPlace place, ClientFactory factory) {
		this.factory = factory;
		parameters = place.getParameters();
	}

	@Override
	public void goTo(Place place) {
		factory.getPlaceController().goTo(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		LeftView leftView = factory.getLeftView();
		leftView.setPresenter(this);
		leftView.setContactsLabel(factory.getI18Constants().contactsButton());
		leftView.setAddressesLabel(factory.getI18Constants().addressesButton());
		leftView.setVehiclesLabel(factory.getI18Constants().vehiclesButton());
		panel.setWidget(leftView);
	}

	public void showContacts() {
		goTo(new ContactsPlace(parameters));
	}

	public void showAddresses() {
		goTo(new AddressesPlace(parameters));
	}

	public void showVehicles() {
		goTo(new VehiclesPlace(parameters));
	}
}