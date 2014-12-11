package com.mvp2.sample.client.mvp.views;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.client.mvp.presenters.AddressesViewPresenter;
import com.mvp2.sample.shared.Address;

public class AddressesView extends Composite implements AddressesViewPresenter.Display {
	private VerticalPanel table;
	private AddressesViewPresenter presenter;

	private Button refreshButton;

	public AddressesView() {
		VerticalPanel panel = new VerticalPanel();
		table = new VerticalPanel();
		table.setBorderWidth(2);
		refreshButton = new Button();
		panel.add(refreshButton);
		panel.add(table);
		initWidget(panel);
		bind();
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(List<Address> addressList) {
		table.clear();
		if(addressList == null) {
			return;
		}
		for (final Address entity : addressList) {
			Label l = new Label("" + entity.getCity() + "(" + entity.getId() + ")");

			l.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					presenter.showAddress(entity.getId());
				}
			});
			table.add(l);
			l.setTitle("" + entity.getId());
		}
	}
	
	@Override
	public void setRefreshButtonLabel(String refreshButtonLabel) {
		this.refreshButton.setText(refreshButtonLabel);
	}


	private void bind() {
		refreshButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.fetchAddresses();
			}
		});
	}

	@Override
	public void setPresenter(AddressesViewPresenter p) {
		presenter = p;
	}
}