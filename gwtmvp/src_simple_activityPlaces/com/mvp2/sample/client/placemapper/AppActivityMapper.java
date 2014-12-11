package com.mvp2.sample.client.placemapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.activity.AddressesViewPresenter;
import com.mvp2.sample.client.mvp.activity.ContactsViewPresenter;
import com.mvp2.sample.client.mvp.activity.LeftViewPresenter;
import com.mvp2.sample.client.mvp.activity.VehiclesViewPresenter;
import com.mvp2.sample.client.places.AddressesPlace;
import com.mvp2.sample.client.places.ContactsPlace;
import com.mvp2.sample.client.places.LeftPlace;
import com.mvp2.sample.client.places.VehiclesPlace;

public class AppActivityMapper implements ActivityMapper {
	private ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof LeftPlace) {
			return new LeftViewPresenter((LeftPlace) place, clientFactory);
		} else if (place instanceof ContactsPlace) {
			return new ContactsViewPresenter((ContactsPlace) place, clientFactory);
		} else if (place instanceof AddressesPlace) {
			return new AddressesViewPresenter((AddressesPlace) place, clientFactory);
		} else if (place instanceof VehiclesPlace) {
			return new VehiclesViewPresenter((VehiclesPlace) place, clientFactory);
		}
		return null;
	}
}