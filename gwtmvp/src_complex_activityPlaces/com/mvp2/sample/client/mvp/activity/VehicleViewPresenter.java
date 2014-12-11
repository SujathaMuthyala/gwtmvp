package com.mvp2.sample.client.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.events.StatusEvent;
import com.mvp2.sample.client.mvp.view.VehicleView;
import com.mvp2.sample.client.places.VehiclePlace;
import com.mvp2.sample.client.places.VehiclesPlace;
import com.mvp2.sample.shared.Vehicle;

public class VehicleViewPresenter extends AbstractActivity implements Presenter {

	private ClientFactory factory;
	private String parameters;
	private VehicleView view;

	public VehicleViewPresenter(VehiclePlace place, ClientFactory factory) {
		this.factory = factory;
		this.parameters = place.getParameters();
	}

	@Override
	public void goTo(Place place) {
		factory.getPlaceController().goTo(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = factory.getVehicleView();
		view.setPresenter(this);
		view.setBackLabel(factory.getI18Constants().backButton());
		view.setVehicleDetailLabel(factory.getI18Constants().vehicleDetail());
		view.setVehicleIdLabel(factory.getI18Constants().vehicleId());
		view.setVehicleCityLabel(factory.getI18Constants().vehicleModel());
		panel.setWidget(view);
		int vehicleId = -1;
		try {
			vehicleId = Integer.parseInt(parameters);
		} catch(Exception ex) {
		}
		factory.getEventBus().fireEvent(new StatusEvent("Loading Address Details for vehicle id " + vehicleId));
		getVehicleDetails(vehicleId);
	}

	private void getVehicleDetails(final int id) {
		factory.getGreetService().getVehicleDetails(id, new AsyncCallback<Vehicle>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Vehicle Details");
				factory.getEventBus().fireEvent(new StatusEvent("Error fetching Vehicle Details for vehicle id " + id));
			}

			@Override
			public void onSuccess(Vehicle result) {
				String entityId = "";
				String entityValue = "";
				if (result != null) {
					entityId = "" + result.getId();
					entityValue = result.getModel();
				}
				view.setVehicleIdValue(entityId);
				view.setVehicleCityValue(entityValue);
				factory.getEventBus().fireEvent(new StatusEvent("Vehicle Details for " + entityValue + "(" + entityId + ") loaded."));
			}
		});
	}

	public void backToVehicleList() {
		goTo(new VehiclesPlace("refresh"));
	}
}