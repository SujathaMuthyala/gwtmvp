package com.mvp2.sample.client.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.view.EastView;

public class EastViewPresenter extends AbstractActivity implements Presenter {
	private ClientFactory factory;
	private Place place;

	public EastViewPresenter(Place place, ClientFactory factory) {
		this.factory = factory;
		this.place = place;
	}

	@Override
	public void goTo(Place place) {
		factory.getPlaceController().goTo(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		EastView view = factory.getEastView();
		/*
		if (place instanceof LeftPlace) {
		} else if (place instanceof ContactsPlace) {
		} else if (place instanceof ContactPlace) {
		} else if (place instanceof AddressesPlace) {
		} else if (place instanceof AddressPlace) {
		} else if (place instanceof VehiclesPlace) {
		} else if (place instanceof VehiclePlace) {
		}
		*/
		panel.setWidget(view);
	}
}