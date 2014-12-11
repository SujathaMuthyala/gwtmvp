package com.mvp2.sample.client.factory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.mvp2.sample.client.i18.I18Constants;
import com.mvp2.sample.client.mvp.view.AddressesView;
import com.mvp2.sample.client.mvp.view.ContactsView;
import com.mvp2.sample.client.mvp.view.LeftView;
import com.mvp2.sample.client.mvp.view.VehiclesView;
import com.mvp2.sample.client.service.GreetingService;
import com.mvp2.sample.client.service.GreetingServiceAsync;

public class ClientFactory {

	private final GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private final I18Constants constants = GWT.create(I18Constants.class);
	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(eventBus);
	private final LeftView leftView = new LeftView();
	private final ContactsView contactsView = new ContactsView();
	private final VehiclesView vehiclesView = new VehiclesView();
	private final AddressesView addressesView = new AddressesView();

	public GreetingServiceAsync getGreetService() {
		return rpcService;
	}

	public I18Constants getI18Constants() {
		return constants;
	}

	public AddressesView getAddressesView() {
		return addressesView;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public PlaceController getPlaceController() {
		return placeController;
	}

	public LeftView getLeftView() {
		return leftView;
	}

	public ContactsView getContactsView() {
		return contactsView;
	}

	public VehiclesView getVehiclesView() {
		return vehiclesView;
	}
}