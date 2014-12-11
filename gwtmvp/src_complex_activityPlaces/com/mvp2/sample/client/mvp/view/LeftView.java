package com.mvp2.sample.client.mvp.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.client.mvp.activity.LeftViewPresenter;

public class LeftView extends Composite {

	private Button btnContacts = new Button("Contacts here");
	private Button btnAddressrs = new Button("Addresses here");
	private Button btnVehicles = new Button("Vehicles here");
	private LeftViewPresenter presenter;

	public LeftView() {
		VerticalPanel parent = new VerticalPanel();
		parent.setSize("100%",  "100%");
		// ScrollPanel parent = new ScrollPanel();
		VerticalPanel panel = new VerticalPanel();
		panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		panel.add(btnContacts);
		panel.add(btnAddressrs);
		panel.add(btnVehicles);
		panel.setSize("100%", "100%");
		parent.add(panel);
		parent.setSize("100%", "100%");
		initWidget(parent);
		bindEvents();
	}

	public void setContactsLabel(String text) {
		btnContacts.setText(text);
	}

	public void setAddressesLabel(String text) {
		btnAddressrs.setText(text);
	}

	public void setVehiclesLabel(String text) {
		btnVehicles.setText(text);
	}

	public Button getContactsButton() {
		return btnContacts;
	}

	public Button getAddressesButton() {
		return btnAddressrs;
	}

	public Button getVehiclesButton() {
		return btnVehicles;
	}

	private void bindEvents() {
		btnContacts.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.showContacts();
			}
		});
		btnAddressrs.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.showAddresses();
			}
		});
		btnVehicles.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.showVehicles();
			}
		});
	}

	public Widget asWidget() {
		return this;
	}

	public void setPresenter(LeftViewPresenter presenter) {
		this.presenter = presenter;
	}
}