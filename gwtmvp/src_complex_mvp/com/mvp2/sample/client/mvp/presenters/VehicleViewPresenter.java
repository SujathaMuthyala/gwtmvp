package com.mvp2.sample.client.mvp.presenters;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.shared.Vehicle;

public class VehicleViewPresenter implements Presenter {

	private Display view;
	private ClientFactory factory;
	private Vehicle vehicle;

	public interface Display extends IsWidget {
		public void setVehicleName(String vehicleName);
		public void setPresenter(VehicleViewPresenter presenter);
	}

	public VehicleViewPresenter(ClientFactory factory, Display view, Vehicle vehicle) {
		this.factory = factory;
		this.view = view;
		this.vehicle = vehicle;
		if(this.vehicle == null) {
			this.vehicle = new Vehicle();
		}
		bind();
	}

	@Override
	public void go(HasWidgets parent) {
		parent.clear();
		parent.add(view.asWidget());
		fetchVehicleDetails();
	}

	public void fetchVehicleDetails() {
		factory.getGreetService().getVehicleDetails(vehicle.getId(), new AsyncCallback<Vehicle>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Vehicle List");
			}

			@Override
			public void onSuccess(Vehicle result) {
				if(result != null) {
					vehicle = result;
					view.setVehicleName(vehicle.toString());
				} else {
					view.setVehicleName("There is no conatct with Vehicle ID : " + vehicle.getId());
				}
			}
		});
	}

	@Override
	public void bind() {
		// As there are  no events happening on the View, 
		// there is no need to have this method or below call.
		view.setPresenter(this);
	}
}