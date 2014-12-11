package com.mvp2.sample.client.mvp.view;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.client.mvp.activity.AddressesViewPresenter;
import com.mvp2.sample.shared.Address;

public class AddressesView extends Composite {

	private VerticalPanel table = new VerticalPanel();
	private Button btnBack = new Button("<--Back");
	private Button btnRefresh = new Button("Refresh");
	private Button btnReverse = new Button("Reverse");
	private Label addressDetailLabel = new Label();
	private AddressesViewPresenter presenter;

	public AddressesView() {
		VerticalPanel panel = new VerticalPanel();
		panel.setSize("100%", "100%");
		panel.addStyleName("westPanel");
		HorizontalPanel hp = new HorizontalPanel();
		panel.add(table);
		panel.add(addressDetailLabel);
		hp.add(btnRefresh);
		hp.add(btnReverse);
		hp.add(btnBack);
		panel.add(hp);
		initWidget(panel);
		bindEvents();
	}

	public void setRefreshLabel(String text) {
		btnRefresh.setText(text);
	}
	public void setBackLabel(String text) {
		btnBack.setText(text);
	}
	public void setReverseLabel(String text) {
		btnReverse.setText(text);
	}

	private void bindEvents() {
		btnBack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.backToHome();
			}
		});
		btnRefresh.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.fetchAddresses();
			}
		});
		btnReverse.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.reverseAddressList();
			}
		});
	}

	public void setData(List<Address> result) {
		table.clear();
		if(result == null || result.size() == 0) {
			return;
		}
		for(Address address : result) {
			table.add(new Label(address.getId() + " ---- "+ address.getCity()));
		}
	}

	public Widget asWidget() {
		return this;
	}

	public void setPresenter(AddressesViewPresenter presenter) {
		this.presenter = presenter;
	}
}