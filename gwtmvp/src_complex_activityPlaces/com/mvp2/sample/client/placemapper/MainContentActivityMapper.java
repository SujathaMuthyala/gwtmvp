package com.mvp2.sample.client.placemapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.activity.AddressViewPresenter;
import com.mvp2.sample.client.mvp.activity.AddressesViewPresenter;
import com.mvp2.sample.client.mvp.activity.ContactViewPresenter;
import com.mvp2.sample.client.mvp.activity.ContactsViewPresenter;
import com.mvp2.sample.client.mvp.activity.VehicleViewPresenter;
import com.mvp2.sample.client.mvp.activity.VehiclesViewPresenter;
import com.mvp2.sample.client.places.AddressPlace;
import com.mvp2.sample.client.places.AddressesPlace;
import com.mvp2.sample.client.places.ContactPlace;
import com.mvp2.sample.client.places.ContactsPlace;
import com.mvp2.sample.client.places.VehiclePlace;
import com.mvp2.sample.client.places.VehiclesPlace;

public class MainContentActivityMapper implements ActivityMapper {
	private ClientFactory clientFactory;

	public MainContentActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof ContactsPlace) {
			return new ContactsViewPresenter((ContactsPlace) place, clientFactory);
		} else if (place instanceof ContactPlace) {
			return new ContactViewPresenter((ContactPlace) place, clientFactory);
		} else if (place instanceof AddressesPlace) {
			return new AddressesViewPresenter((AddressesPlace) place, clientFactory);
		} else if (place instanceof AddressPlace) {
			return new AddressViewPresenter((AddressPlace) place, clientFactory);
		} else if (place instanceof VehiclesPlace) {
			return new VehiclesViewPresenter((VehiclesPlace) place, clientFactory);
		} else if (place instanceof VehiclePlace) {
			return new VehicleViewPresenter((VehiclePlace) place, clientFactory);
		}
		return null;
	}
}