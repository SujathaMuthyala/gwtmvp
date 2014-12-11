package com.mvp2.sample.client.mvp.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.client.mvp.presenters.VehicleViewPresenter;

public class VehicleView extends Composite implements VehicleViewPresenter.Display {
	private Label nameLabel;
	private Label label;

	// As there are  no events happening on the View, 
	// there is no need to have this variable.
	// private VehicleViewPresenter presenter;

	public VehicleView() {
		HorizontalPanel vp = new HorizontalPanel();
		nameLabel = new Label();
		label = new Label();
		vp.add(nameLabel);
		vp.add(label);
		initWidget(vp);
		bind();
	}

	private void bind() {
	}

	public Widget asWidget(){
		return this;
	}

	@Override
	public void setVehicleName(String vehicleName) {
		label.setText(vehicleName);
	}

	@Override
	public void setPresenter(VehicleViewPresenter p) {
		// As there are  no events happening on the View, 
		// there is no need to have this method or below call.
		// presenter = p;
	}
}