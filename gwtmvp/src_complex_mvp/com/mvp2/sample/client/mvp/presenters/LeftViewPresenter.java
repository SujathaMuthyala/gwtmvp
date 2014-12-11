package com.mvp2.sample.client.mvp.presenters;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.events.ShowAddressesEvent;
import com.mvp2.sample.client.mvp.events.ShowContactsEvent;
import com.mvp2.sample.client.mvp.events.ShowVehiclesEvent;

public class LeftViewPresenter implements Presenter {

	private Display view;
	private ClientFactory factory;

	public interface Display extends IsWidget {
		public void setContactsButtonLabel(String text);
		public void setAddressesButtonLabel(String text);
		public void setVehiclesButtonLabel(String text);
		public void setPresenter(LeftViewPresenter p);
	}

	public LeftViewPresenter(ClientFactory factory, Display view) {
		this.factory = factory;
		this.view = view;
	}

	@Override
	public void go(HasWidgets parent) {
		bind();
		parent.clear();
		view.setContactsButtonLabel(factory.getI18Constants().contactsButton());
		view.setAddressesButtonLabel(factory.getI18Constants().addressesButton());
		view.setVehiclesButtonLabel(factory.getI18Constants().vehiclesButton());
		parent.add(view.asWidget());

	}

	@Override
	public void bind() {
		view.setPresenter(this);
	}
	
	public void showContacts() {
		factory.getEventBus().fireEvent(new ShowContactsEvent());
	}

	public void showVehicles() {
		factory.getEventBus().fireEvent(new ShowVehiclesEvent());
	}

	public void showAddresses() {
		factory.getEventBus().fireEvent(new ShowAddressesEvent());
	}
}