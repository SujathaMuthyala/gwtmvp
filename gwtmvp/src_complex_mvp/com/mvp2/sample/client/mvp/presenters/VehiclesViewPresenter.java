package com.mvp2.sample.client.mvp.presenters;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.events.ShowVehicleEvent;
import com.mvp2.sample.shared.Vehicle;

public class VehiclesViewPresenter implements Presenter {

	private Display view;
	private ClientFactory factory;
	// private List<Vehicle> vehicleList;

	public interface Display extends IsWidget {
		public void setData(List<Vehicle> vehicleList);
		public void setRefreshButtonLabel(String RefreshButtonLabel);
		public void setPresenter(VehiclesViewPresenter p);
	}

	public VehiclesViewPresenter(ClientFactory factory, Display view) {
		this.factory = factory;
		this.view = view;
		bind();
	}

	@Override
	public void go(HasWidgets parent) {
		parent.clear();
		fetchVehicles();
		this.view.setRefreshButtonLabel(factory.getI18Constants().refresh());
		parent.add(view.asWidget());
	}

	public void fetchVehicles() {
		factory.getGreetService().getVehicleList(new AsyncCallback<List<Vehicle>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Vehicle List");
			}

			@Override
			public void onSuccess(List<Vehicle> result) {
				// vehicleList = result;
				view.setData(result);
			}
		});
	}

	public void showVehicle(int id) {
		factory.getEventBus().fireEvent(new ShowVehicleEvent(id));
	}

	@Override
	public void bind() {
		view.setPresenter(this);
	}
}