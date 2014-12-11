package com.mvp2.sample.client.factory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.mvp2.sample.client.i18.I18Constants;
import com.mvp2.sample.client.mvp.view.AddressView;
import com.mvp2.sample.client.mvp.view.AddressesView;
import com.mvp2.sample.client.mvp.view.ContactView;
import com.mvp2.sample.client.mvp.view.ContactsView;
import com.mvp2.sample.client.mvp.view.EastView;
import com.mvp2.sample.client.mvp.view.LeftView;
import com.mvp2.sample.client.mvp.view.NorthView;
import com.mvp2.sample.client.mvp.view.SouthView;
import com.mvp2.sample.client.mvp.view.VehicleView;
import com.mvp2.sample.client.mvp.view.VehiclesView;
import com.mvp2.sample.client.service.GreetingService;
import com.mvp2.sample.client.service.GreetingServiceAsync;

public class ClientFactory {

	private final GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private final I18Constants constants = GWT.create(I18Constants.class);
	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(eventBus);
	private final LeftView leftView = new LeftView();
	private final AddressesView addressesView = new AddressesView();
	private final AddressView addressView = new AddressView();

	private final ContactView contactView = new ContactView();
	private final ContactsView contactsView = new ContactsView();

	private final VehicleView vehicleView = new VehicleView();
	private final VehiclesView vehiclesView = new VehiclesView();

	private final SouthView southView = new SouthView(eventBus);
	private final NorthView northView = new NorthView(eventBus);
	private final EastView eastView = new EastView(eventBus);

	public GreetingServiceAsync getGreetService() {
		return rpcService;
	}

	public I18Constants getI18Constants() {
		return constants;
	}

	public AddressesView getAddressesView() {
		return addressesView;
	}

	public SouthView getSouthView() {
		return southView;
	}

	public EastView getEastView() {
		return eastView;
	}

	public NorthView getNorthView() {
		return northView;
	}

	public AddressView getAddressView() {
		return addressView;
	}

	public VehicleView getVehicleView() {
		return vehicleView;
	}
	public ContactView getContactView() {
		return contactView;
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