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
import com.mvp2.sample.client.mvp.view.AddressesView;
import com.mvp2.sample.client.places.AddressPlace;
import com.mvp2.sample.client.places.AddressesPlace;
import com.mvp2.sample.client.places.LeftPlace;
import com.mvp2.sample.shared.Address;

public class AddressesViewPresenter extends AbstractActivity implements Presenter {

	private ClientFactory factory;
	private String parameters;
	private AddressesView view;

	public AddressesViewPresenter(AddressesPlace place, ClientFactory factory) {
		this.factory = factory;
		this.parameters = place.getParameters();
	}

	@Override
	public void goTo(Place place) {
		factory.getPlaceController().goTo(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		factory.getEventBus().fireEvent(new StatusEvent("Loading Address List"));
		view = factory.getAddressesView();
		view.setPresenter(this);
		view.setBackLabel(factory.getI18Constants().backButton());
		view.setRefreshLabel(factory.getI18Constants().refreshButton());
		view.setReverseLabel(factory.getI18Constants().reverseButton());
		view.setVisible(false);
		panel.setWidget(view);
		if(parameters != null) {
			if(parameters.trim().equalsIgnoreCase("reverse")) {
				getReverseAddresses();
			} else {
				getAllAddresses();
			}
		}
	}
	public void fetchAddresses() {
		goTo(new AddressesPlace("refresh"));
	}

	private void getAllAddresses() {
		factory.getGreetService().getAddressList(new AsyncCallback<List<Address>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Address List");
				factory.getEventBus().fireEvent(new StatusEvent("Error loading Address List"));
			}

			@Override
			public void onSuccess(List<Address> result) {
				view.setData(result);
				view.setVisible(true);
				factory.getEventBus().fireEvent(new StatusEvent("Loaded Address List successfully"));
			}
		});
	}

	public void backToHome() {
		goTo(new LeftPlace(""));
	}
	
	public void showAddress(int id) {
		goTo(new AddressPlace("" + id));
	}

	public void reverseAddressList() {
		goTo(new AddressesPlace("reverse"));
	}

	private void getReverseAddresses() {
		factory.getGreetService().getAddressList(new AsyncCallback<List<Address>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Address List");
				factory.getEventBus().fireEvent(new StatusEvent("Error loading Reverse Address List"));
			}

			@Override
			public void onSuccess(List<Address> result) {
				Collections.sort(result);
				Collections.reverse(result);
				view.setData(result);
				factory.getEventBus().fireEvent(new StatusEvent("Loaded Reverse Address List"));
				view.setVisible(true);
			}
		});
	}
}