package com.mvp2.sample.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.events.ShowAddressEvent;
import com.mvp2.sample.client.mvp.events.ShowAddressEventHandler;
import com.mvp2.sample.client.mvp.events.ShowAddressesEvent;
import com.mvp2.sample.client.mvp.events.ShowAddressesEventHandler;
import com.mvp2.sample.client.mvp.events.ShowContactEvent;
import com.mvp2.sample.client.mvp.events.ShowContactEventHandler;
import com.mvp2.sample.client.mvp.events.ShowContactsEvent;
import com.mvp2.sample.client.mvp.events.ShowContactsEventHandler;
import com.mvp2.sample.client.mvp.events.ShowVehicleEvent;
import com.mvp2.sample.client.mvp.events.ShowVehicleEventHandler;
import com.mvp2.sample.client.mvp.events.ShowVehiclesEvent;
import com.mvp2.sample.client.mvp.events.ShowVehiclesEventHandler;
import com.mvp2.sample.client.mvp.presenters.AddressViewPresenter;
import com.mvp2.sample.client.mvp.presenters.AddressesViewPresenter;
import com.mvp2.sample.client.mvp.presenters.ContactViewPresenter;
import com.mvp2.sample.client.mvp.presenters.ContactsViewPresenter;
import com.mvp2.sample.client.mvp.presenters.LeftViewPresenter;
import com.mvp2.sample.client.mvp.presenters.MainViewPresenter;
import com.mvp2.sample.client.mvp.presenters.VehicleViewPresenter;
import com.mvp2.sample.client.mvp.presenters.VehiclesViewPresenter;
import com.mvp2.sample.client.mvp.views.MainView;
import com.mvp2.sample.shared.Address;
import com.mvp2.sample.shared.Contact;
import com.mvp2.sample.shared.Vehicle;

public class HistoryManager implements ValueChangeHandler<String> {

	public static final String HOME = "HOME";
	public static final String CONTACTS = "CONTACTS";
	public static final String VIEW_CONTACT = "VIEW_CONTACT";
	public static final String VIEW_CONTACT_PARAM = VIEW_CONTACT + "&ID=";

	public static final String ADDRESSES = "ADDRESSES";
	public static final String VIEW_ADDRESS = "VIEW_ADDRESS";
	public static final String VIEW_ADDRESS_PARAM = VIEW_ADDRESS + "&ID=";

	public static final String VEHICLES = "VEHICLES";
	public static final String VIEW_VEHICLE = "VIEW_VEHICLE";
	public static final String VIEW_VEHICLE_PARAM = VIEW_VEHICLE + "&ID=";

	private final ClientFactory factory = GWT.create(ClientFactory.class);

	private Panel centerPlace;
	private Panel northPlace;
	private Panel southPlace;
	private Panel eastPlace;
	private Panel westPlace;
	private MainView mainView;

	public HistoryManager() {
		mainView = factory.getMainView();
		northPlace = mainView.getNoth();
		southPlace = mainView.getSouth();
		eastPlace = mainView.getEast();
		westPlace = mainView.getWest();
		centerPlace = mainView.getCenterBody();
		RootPanel.get().clear();
		RootLayoutPanel.get().add(mainView);

		bindGlobalEvents();
	}

	private void bindGlobalEvents() {
		factory.getEventBus().addHandler(ShowAddressesEvent.TYPE, new ShowAddressesEventHandler() {
			@Override
			public void onShowAddresses(ShowAddressesEvent event) {
				History.newItem(ADDRESSES);
			}
		});
		factory.getEventBus().addHandler(ShowAddressEvent.TYPE, new ShowAddressEventHandler() {
			@Override
			public void onShowAddress(ShowAddressEvent event) {
				History.newItem(VIEW_ADDRESS_PARAM + event.getId());
			}
		});
		factory.getEventBus().addHandler(ShowVehiclesEvent.TYPE, new ShowVehiclesEventHandler() {
			@Override
			public void onShowVehicles(ShowVehiclesEvent event) {
				History.newItem(VEHICLES);
			}
		});
		factory.getEventBus().addHandler(ShowVehicleEvent.TYPE, new ShowVehicleEventHandler() {
			@Override
			public void onShowVehicle(ShowVehicleEvent event) {
				History.newItem(VIEW_VEHICLE_PARAM + event.getId());
			}
		});
		factory.getEventBus().addHandler(ShowContactsEvent.TYPE, new ShowContactsEventHandler() {
			@Override
			public void onShowContacts(ShowContactsEvent event) {
				History.newItem(CONTACTS);
			}
		});
		factory.getEventBus().addHandler(ShowContactEvent.TYPE, new ShowContactEventHandler() {
			@Override
			public void onShowContact(ShowContactEvent event) {
				History.newItem(VIEW_CONTACT_PARAM + event.getId());
			}
		});
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {

		String historyToken = event.getValue();
		mainView.setUpLayout();
		if (historyToken.startsWith(HOME)) {
			showLayout();
			showHome();
		} else if (historyToken.startsWith(CONTACTS)) {
			showLayout();
			showHome();
			showContacts();
		} else if (historyToken.startsWith(VIEW_CONTACT)) {
			showLayout();
			showHome();
			showContacts();
			showContact(historyToken);
		} else if (historyToken.startsWith(ADDRESSES)) {
			showLayout();
			showHome();
			showAddresses();
		} else if (historyToken.startsWith(VIEW_ADDRESS)) {
			showLayout();
			showHome();
			showAddresses();
			showAddress(historyToken);
		} else if (historyToken.startsWith(VEHICLES)) {
			showLayout();
			showHome();
			showVehicles();
		} else if (historyToken.startsWith(VIEW_VEHICLE)) {
			showLayout();
			showHome();
			showVehicles();
			showVehicle(historyToken);
		} else {
			showHome();
		}
	}

	private void showLayout() {
		MainViewPresenter presenter = new MainViewPresenter(factory, mainView);
		presenter.go(RootLayoutPanel.get());
	}

	private void showHome() {
		centerPlace.clear();
		northPlace.clear();
		southPlace.clear();
		eastPlace.clear();
		// westPlace.clear();
		mainView.animate(500);
		mainView.setWidgetSize(eastPlace, 0);

		LeftViewPresenter lPresenter = new LeftViewPresenter(factory, factory.getLeftView());
		lPresenter.go(westPlace);
	}

	private void showContacts() {
		ContactsViewPresenter cPresenter = new ContactsViewPresenter(factory, factory.getContactsView());
		cPresenter.go(centerPlace);
	}

	private void showContact(String historyToken) {
		int contactId = getEntityID(historyToken, HistoryManager.VIEW_CONTACT_PARAM);
		Contact contact = new Contact(contactId, "");
		ContactViewPresenter cPresenter = new ContactViewPresenter(factory, factory.getContactView(), contact);
		cPresenter.go(eastPlace);
		mainView.setWidgetSize(eastPlace, 200);
	}

	private void showAddresses() {
		AddressesViewPresenter cPresenter = new AddressesViewPresenter(factory, factory.getAddressesView());
		cPresenter.go(centerPlace);
	}

	private void showAddress(String historyToken) {
		int addressId = getEntityID(historyToken, HistoryManager.VIEW_ADDRESS_PARAM);
		Address address = new Address(addressId, "");
		AddressViewPresenter cPresenter = new AddressViewPresenter(factory, factory.getAddressView(), address);
		cPresenter.go(eastPlace);
		mainView.animate(500);
		mainView.setWidgetSize(eastPlace, 200);
	}

	private void showVehicles() {
		VehiclesViewPresenter cPresenter = new VehiclesViewPresenter(factory, factory.getVehiclesView());
		cPresenter.go(centerPlace);
	}

	private void showVehicle(String historyToken) {
		int entityId = getEntityID(historyToken, HistoryManager.VIEW_VEHICLE_PARAM);
		Vehicle vehicle = new Vehicle(entityId, "");
		VehicleViewPresenter cPresenter = new VehicleViewPresenter(factory, factory.getVehicleView(), vehicle);
		cPresenter.go(eastPlace);
		mainView.animate(500);
		mainView.setWidgetSize(eastPlace, 200);
	}

	private int getEntityID(String historyToken, String key) {
		int id = -1;
		String strId = historyToken.substring(key.length());
		try {
			id = Integer.parseInt(strId);
		} catch (Exception ex) {
			Window.alert("Invalid ID passed : '" + strId + "' passed for '" + key + "'");
		}
		return id;
	}
}