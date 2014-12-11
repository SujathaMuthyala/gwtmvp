package com.mvp2.sample.client.mvp.presenters;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.shared.Address;

public class AddressViewPresenter implements Presenter {

	private Display view;
	private ClientFactory factory;
	private Address address;

	public interface Display extends IsWidget {
		public void setAddressName(String addressName);
		public void setPresenter(AddressViewPresenter presenter);
	}

	public AddressViewPresenter(ClientFactory factory, Display view, Address address) {
		this.factory = factory;
		this.view = view;
		this.address = address;
		if(this.address == null) {
			this.address = new Address();
		}
		bind();
	}

	@Override
	public void go(HasWidgets parent) {
		parent.clear();
		parent.add(view.asWidget());
		fetchAddressDetails();
	}

	public void fetchAddressDetails() {
		factory.getGreetService().getAddressDetails(address.getId(), new AsyncCallback<Address>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Address List");
			}

			@Override
			public void onSuccess(Address result) {
				if(result != null) {
					address = result;
					view.setAddressName(address.toString());
				} else {
					view.setAddressName("There is no conatct with Address ID : " + address.getId());
				}
			}
		});
	}

	@Override
	public void bind() {
		// As there are  no events happening on the View, 
		// there is no need to have this method or below call.
		view.setPresenter(this);
	}
}