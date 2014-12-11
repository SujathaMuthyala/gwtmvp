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
import com.mvp2.sample.client.mvp.view.VehiclesView;
import com.mvp2.sample.client.places.LeftPlace;
import com.mvp2.sample.client.places.VehiclesPlace;
import com.mvp2.sample.shared.Vehicle;

public class VehiclesViewPresenter extends AbstractActivity implements Presenter {

	private ClientFactory factory;
	private String parameters;
	private VehiclesView view;

	public VehiclesViewPresenter(VehiclesPlace place, ClientFactory factory) {
		this.factory = factory;
		this.parameters = place.getParameters();
	}

	@Override
	public void goTo(Place place) {
		factory.getPlaceController().goTo(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = factory.getVehiclesView();
		view.setPresenter(this);
		view.setBackLabel(factory.getI18Constants().backButton());
		view.setRefreshLabel(factory.getI18Constants().refreshButton());
		view.setReverseLabel(factory.getI18Constants().reverseButton());
		fetchVehicles();
		panel.setWidget(view);
	}

	public void fetchVehicles() {
		factory.getGreetService().getVehicleList(new AsyncCallback<List<Vehicle>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Vehicle List");
			}

			@Override
			public void onSuccess(List<Vehicle> result) {
				view.setData(result);
			}
		});
	}

	public void backToHome() {
		goTo(new LeftPlace(parameters));
	}

	public void reverseVehicleList() {
		factory.getGreetService().getVehicleList(new AsyncCallback<List<Vehicle>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Vehicle List");
			}

			@Override
			public void onSuccess(List<Vehicle> result) {
				Collections.sort(result);
				Collections.reverse(result);
				view.setData(result);
			}
		});
	}
}