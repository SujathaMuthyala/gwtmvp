package com.mvp2.sample.client.mvp.views;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.client.mvp.presenters.LeftViewPresenter;

public class LeftView extends Composite implements LeftViewPresenter.Display {
	
	private Button btnContacts = new Button();
	private Button btnVehicles = new Button();
	private Button btnAddresses = new Button();
	private LeftViewPresenter presenter;

	public LeftView() {
		VerticalPanel panel = new VerticalPanel();
		panel.add(btnContacts);
		panel.add(btnAddresses);
		panel.add(btnVehicles);
		initWidget(panel);
		bind();
	}
	
	private void bind() {
		btnContacts.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.showContacts();
			}
		});
		btnVehicles.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.showVehicles();
			}
		});
		btnAddresses.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.showAddresses();
			}
		});
	}

	public Widget asWidget(){
		return this;
	}
	
	public void setContactsButtonLabel(String text) {
		btnContacts.setText(text);
	}

	public void setVehiclesButtonLabel(String text) {
		btnVehicles.setText(text);
	}

	@Override
	public void setAddressesButtonLabel(String text) {
		btnAddresses.setText(text);
	}

	@Override
	public void setPresenter(LeftViewPresenter p) {
		this.presenter = p;
	}
}