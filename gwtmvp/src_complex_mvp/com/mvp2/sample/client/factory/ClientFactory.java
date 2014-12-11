package com.mvp2.sample.client.factory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.mvp2.sample.client.i18.I18Constants;
import com.mvp2.sample.client.mvp.views.AddressView;
import com.mvp2.sample.client.mvp.views.AddressesView;
import com.mvp2.sample.client.mvp.views.ContactView;
import com.mvp2.sample.client.mvp.views.ContactsView;
import com.mvp2.sample.client.mvp.views.LeftView;
import com.mvp2.sample.client.mvp.views.MainView;
import com.mvp2.sample.client.mvp.views.VehicleView;
import com.mvp2.sample.client.mvp.views.VehiclesView;
import com.mvp2.sample.client.service.GreetingService;
import com.mvp2.sample.client.service.GreetingServiceAsync;

public class ClientFactory {

	private final GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private final EventBus eventBus = new SimpleEventBus();
	private final I18Constants i18Constants = GWT.create(I18Constants.class);

	private final MainView mainView = new MainView(Unit.PX);
	private final LeftView leftView = new LeftView();

	private final ContactsView contactsView = new ContactsView();
	private final AddressesView addressesView = new AddressesView();
	private final VehiclesView vehiclesView = new VehiclesView();

	private final ContactView contactView = new ContactView();
	private final AddressView addressView = new AddressView();
	private final VehicleView vehicleView = new VehicleView();

	public LeftView getLeftView() {
		return leftView;
	}

	public ContactsView getContactsView() {
		return contactsView;
	}

	public ContactView getContactView() {
		return contactView;
	}

	public AddressView getAddressView() {
		return addressView;
	}

	public VehicleView getVehicleView() {
		return vehicleView;
	}

	public AddressesView getAddressesView() {
		return addressesView;
	}

	public VehiclesView getVehiclesView() {
		return vehiclesView;
	}

	public I18Constants getI18Constants() {
		return i18Constants;
	}

	public GreetingServiceAsync getGreetService() {
		return rpcService;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public MainView getMainView() {
		return mainView;
	}
}