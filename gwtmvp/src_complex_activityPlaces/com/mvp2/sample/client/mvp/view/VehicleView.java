package com.mvp2.sample.client.mvp.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.client.mvp.activity.VehicleViewPresenter;

public class VehicleView extends Composite {

	private HTML vehicleDetailLabel = new HTML();
	private HTML vehicleIdLabel = new HTML();
	private HTML vehicleCityLabel = new HTML();

	private Label vehicleIdValue = new Label();
	private Label vehicleCityValue = new Label();

	private Button btnBack = new Button("<--Back");

	private VehicleViewPresenter presenter;

	public VehicleView() {
		ScrollPanel main = new ScrollPanel();
		VerticalPanel panel = new VerticalPanel();
		panel.setSize("100%", "100%");

		FlexTable flexTable = new FlexTable();
		flexTable.setBorderWidth(2);
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(3);
		flexTable.setWidget(0, 0, vehicleDetailLabel);
		cellFormatter.setColSpan(0, 0, 2);

		flexTable.setWidget(1, 0, vehicleIdLabel);
		flexTable.setWidget(2, 0, vehicleCityLabel);

		flexTable.setWidget(1, 1, vehicleIdValue);
		flexTable.setWidget(2, 1, vehicleCityValue);

		flexTable.setWidget(3, 0, btnBack);
		cellFormatter.setColSpan(3, 0, 2);

		flexTable.setSize("60%",  "100%");
		flexTable.getColumnFormatter().setWidth(0, "50%");

		panel.add(flexTable);
		main.add(panel);
		initWidget(main);
		bindEvents();
	}

	public void setVehicleDetailLabel(String text) {
		vehicleDetailLabel.setHTML(text);
	}

	public void setBackLabel(String text) {
		btnBack.setText(text);
	}

	public void setVehicleIdLabel(String text) {
		vehicleIdLabel.setHTML(text);
	}

	public void setVehicleCityLabel(String text) {
		vehicleCityLabel.setHTML(text);
	}

	public void setVehicleIdValue(String text) {
		vehicleIdValue.setText(text);
	}

	public void setVehicleCityValue(String text) {
		vehicleCityValue.setText(text);
	}

	private void bindEvents() {
		btnBack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.backToVehicleList();
			}
		});
	}

	public Widget asWidget() {
		return this;
	}

	public void setPresenter(VehicleViewPresenter presenter) {
		this.presenter = presenter;
	}
}