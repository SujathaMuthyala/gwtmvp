package com.mvp2.sample.client.mvp.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.client.mvp.activity.LeftViewPresenter;

public class LeftView extends Composite {

	private Button btnContacts = new Button("Contacts here");
	private Button btnAddressrs = new Button("Addresses here");
	private Button btnVehicles = new Button("Vehicles here");
	private LeftViewPresenter presenter;

	public LeftView() {
		VerticalPanel panel = new VerticalPanel();
		panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		SimplePanel sp = new SimplePanel();
		VerticalPanel innerpanel = new VerticalPanel();
		innerpanel.add(btnContacts);
		innerpanel.add(btnAddressrs);
		innerpanel.add(btnVehicles);
		sp.add(innerpanel);
		panel.add(sp);
		panel.setSize("100%", "100%");
		panel.addStyleName("northPanel");
		initWidget(panel);
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