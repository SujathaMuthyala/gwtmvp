package com.mvp2.sample.client.placemapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.activity.EastViewPresenter;
import com.mvp2.sample.client.places.AddressPlace;
import com.mvp2.sample.client.places.AddressesPlace;
import com.mvp2.sample.client.places.ContactPlace;
import com.mvp2.sample.client.places.ContactsPlace;
import com.mvp2.sample.client.places.LeftPlace;
import com.mvp2.sample.client.places.VehiclePlace;
import com.mvp2.sample.client.places.VehiclesPlace;

public class EastContentActivityMapper implements ActivityMapper {
	private ClientFactory clientFactory;

	public EastContentActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		System.out.println("################# EastContentActivityMapper  ====" + place.getClass());
		if (place instanceof LeftPlace) {
			// return new EastViewPresenter(place, clientFactory);
			return null;
		} else if (place instanceof ContactsPlace) {
			return new EastViewPresenter(place, clientFactory);
		} else if (place instanceof ContactPlace) {
			return null;
			// return new EastViewPresenter(place, clientFactory);
		} else if (place instanceof AddressesPlace) {
			return new EastViewPresenter(place, clientFactory);
		} else if (place instanceof AddressPlace) {
			return null;
			// return new EastViewPresenter(place, clientFactory);
		} else if (place instanceof VehiclesPlace) {
			return new EastViewPresenter(place, clientFactory);
		} else if (place instanceof VehiclePlace) {
			return null;
			// return new EastViewPresenter(place, clientFactory);
		}
		return null;
	}
}