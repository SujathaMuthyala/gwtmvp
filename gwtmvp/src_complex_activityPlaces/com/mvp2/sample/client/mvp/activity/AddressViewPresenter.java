package com.mvp2.sample.client.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.events.StatusEvent;
import com.mvp2.sample.client.mvp.view.AddressView;
import com.mvp2.sample.client.places.AddressPlace;
import com.mvp2.sample.client.places.AddressesPlace;
import com.mvp2.sample.shared.Address;

public class AddressViewPresenter extends AbstractActivity implements Presenter {

	private ClientFactory factory;
	private String parameters;
	private AddressView view;

	public AddressViewPresenter(AddressPlace place, ClientFactory factory) {
		this.factory = factory;
		this.parameters = place.getParameters();
	}

	@Override
	public void goTo(Place place) {
		factory.getPlaceController().goTo(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = factory.getAddressView();
		view.setPresenter(this);
		view.setBackLabel(factory.getI18Constants().backButton());
		view.setAddressDetailLabel(factory.getI18Constants().addressDetail());
		view.setAddressIdLabel(factory.getI18Constants().addressId());
		view.setAddressCityLabel(factory.getI18Constants().addressCity());
		panel.setWidget(view);
		int addressId = -1;
		try {
			addressId = Integer.parseInt(parameters);
		} catch(Exception ex) {
		}
		factory.getEventBus().fireEvent(new StatusEvent("Loading Address Details for address id " + addressId));
		getAddressDetails(addressId);
	}

	private void getAddressDetails(final int id) {
		factory.getGreetService().getAddressDetails(id, new AsyncCallback<Address>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Address Details");
				factory.getEventBus().fireEvent(new StatusEvent("Error fetching Address Details for address id " + id));
			}

			@Override
			public void onSuccess(Address result) {
				String entityId = "";
				String entityValue = "";
				if (result != null) {
					entityId = "" + result.getId();
					entityValue = result.getCity();
				}
				view.setAddressIdValue(entityId);
				view.setAddressCityValue(entityValue);
				factory.getEventBus().fireEvent(new StatusEvent("Address Details for " + entityValue + "(" + entityId + ") loaded."));
			}
		});
	}

	public void backToAddressList() {
		goTo(new AddressesPlace("refresh"));
	}
}