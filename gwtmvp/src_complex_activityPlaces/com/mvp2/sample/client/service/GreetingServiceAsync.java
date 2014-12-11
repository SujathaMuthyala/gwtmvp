package com.mvp2.sample.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp2.sample.shared.Address;
import com.mvp2.sample.shared.Contact;
import com.mvp2.sample.shared.Vehicle;

public interface GreetingServiceAsync {
	void getContactList(AsyncCallback<List<Contact>> callback);

	void getContactDetails(int id, AsyncCallback<Contact> callback);

	void getAddressDetails(int id, AsyncCallback<Address> callback);

	void getAddressList(AsyncCallback<List<Address>> callback);

	void getVehicleDetails(int id, AsyncCallback<Vehicle> callback);

	void getVehicleList(AsyncCallback<List<Vehicle>> callback);
}