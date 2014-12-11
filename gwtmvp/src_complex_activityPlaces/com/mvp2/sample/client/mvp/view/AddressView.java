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
import com.mvp2.sample.client.mvp.activity.AddressViewPresenter;

public class AddressView extends Composite {

	private HTML addressDetailLabel = new HTML();
	private HTML addressIdLabel = new HTML();
	private HTML addressCityLabel = new HTML();

	private Label addressIdValue = new Label();
	private Label addressCityValue = new Label();

	private Button btnBack = new Button("<--Back");

	private AddressViewPresenter presenter;

	public AddressView() {
		ScrollPanel main = new ScrollPanel();
		VerticalPanel panel = new VerticalPanel();
		panel.setSize("100%", "100%");

		FlexTable flexTable = new FlexTable();
		flexTable.setBorderWidth(2);
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(3);
		flexTable.setWidget(0, 0, addressDetailLabel);
		cellFormatter.setColSpan(0, 0, 2);

		flexTable.setWidget(1, 0, addressIdLabel);
		flexTable.setWidget(2, 0, addressCityLabel);

		flexTable.setWidget(1, 1, addressIdValue);
		flexTable.setWidget(2, 1, addressCityValue);

		flexTable.setWidget(3, 0, btnBack);
		cellFormatter.setColSpan(3, 0, 2);

		flexTable.setSize("60%",  "100%");
		flexTable.getColumnFormatter().setWidth(0, "50%");

		panel.add(flexTable);
		main.add(panel);
		initWidget(main);
		bindEvents();
	}

	public void setAddressDetailLabel(String text) {
		addressDetailLabel.setHTML(text);
	}

	public void setBackLabel(String text) {
		btnBack.setText(text);
	}

	public void setAddressIdLabel(String text) {
		addressIdLabel.setHTML(text);
	}

	public void setAddressCityLabel(String text) {
		addressCityLabel.setHTML(text);
	}

	public void setAddressIdValue(String text) {
		addressIdValue.setText(text);
	}

	public void setAddressCityValue(String text) {
		addressCityValue.setText(text);
	}

	private void bindEvents() {
		btnBack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.backToAddressList();
			}
		});
	}

	public Widget asWidget() {
		return this;
	}

	public void setPresenter(AddressViewPresenter presenter) {
		this.presenter = presenter;
	}
}