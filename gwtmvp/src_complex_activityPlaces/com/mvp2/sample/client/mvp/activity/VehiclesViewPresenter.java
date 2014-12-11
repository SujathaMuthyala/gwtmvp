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
import com.mvp2.sample.client.mvp.view.VehiclesView;
import com.mvp2.sample.client.places.VehiclePlace;
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
		factory.getEventBus().fireEvent(new StatusEvent("Loading Vehicle List"));
		view = factory.getVehiclesView();
		view.setVisible(false);
		view.setPresenter(this);
		view.setBackLabel(factory.getI18Constants().backButton());
		view.setRefreshLabel(factory.getI18Constants().refreshButton());
		view.setReverseLabel(factory.getI18Constants().reverseButton());
		panel.setWidget(view);
		if(parameters != null) {
			if(parameters.trim().equalsIgnoreCase("reverse")) {
				getReverseVehicles();
			} else {
				getAllVehicles();
			}
		}
	}
	public void fetchVehicles() {
		goTo(new VehiclesPlace("refresh"));
	}

	private void getAllVehicles() {
		factory.getGreetService().getVehicleList(new AsyncCallback<List<Vehicle>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Vehicle List");
				factory.getEventBus().fireEvent(new StatusEvent("Error loading Vehicle List"));
			}

			@Override
			public void onSuccess(List<Vehicle> result) {
				view.setData(result);
				view.setVisible(true);
				factory.getEventBus().fireEvent(new StatusEvent("Loaded Vehicle List successfully"));
			}
		});
	}

	public void backToHome() {
		goTo(new LeftPlace(""));
	}
	
	public void showVehicle(int id) {
		goTo(new VehiclePlace("" + id));
	}

	public void reverseVehicleList() {
		goTo(new VehiclesPlace("reverse"));
	}

	private void getReverseVehicles() {
		factory.getGreetService().getVehicleList(new AsyncCallback<List<Vehicle>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Vehicle List");
				factory.getEventBus().fireEvent(new StatusEvent("Error loading Reverse Vehicle List"));
			}

			@Override
			public void onSuccess(List<Vehicle> result) {
				Collections.sort(result);
				Collections.reverse(result);
				view.setData(result);
				view.setVisible(true);
				factory.getEventBus().fireEvent(new StatusEvent("Loaded Reverse Vehicle List"));
			}
		});
	}
}