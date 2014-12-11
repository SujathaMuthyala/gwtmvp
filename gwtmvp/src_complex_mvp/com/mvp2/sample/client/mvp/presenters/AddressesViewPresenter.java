package com.mvp2.sample.client.mvp.presenters;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.events.ShowAddressEvent;
import com.mvp2.sample.shared.Address;

public class AddressesViewPresenter implements Presenter {

	private Display view;
	private ClientFactory factory;
	//  private List<Address> addressList;

	public interface Display extends IsWidget {
		public void setData(List<Address> addressList);
		public void setRefreshButtonLabel(String RefreshButtonLabel);
		public void setPresenter(AddressesViewPresenter p);
	}

	public AddressesViewPresenter(ClientFactory factory, Display view) {
		this.factory = factory;
		this.view = view;
		bind();
	}

	@Override
	public void go(HasWidgets parent) {
		parent.clear();
		fetchAddresses();
		this.view.setRefreshButtonLabel(factory.getI18Constants().refresh());
		parent.add(view.asWidget());
	}

	public void fetchAddresses() {
		factory.getGreetService().getAddressList(new AsyncCallback<List<Address>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Address List");
			}

			@Override
			public void onSuccess(List<Address> result) {
				// addressList = result;
				view.setData(result);
			}
		});
	}

	public void showAddress(int id) {
		factory.getEventBus().fireEvent(new ShowAddressEvent(id));
	}

	@Override
	public void bind() {
		view.setPresenter(this);
	}
}