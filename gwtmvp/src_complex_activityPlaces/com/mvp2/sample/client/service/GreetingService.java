package com.mvp2.sample.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mvp2.sample.shared.Address;
import com.mvp2.sample.shared.Contact;
import com.mvp2.sample.shared.Vehicle;

@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	Address getAddressDetails(int id) throws IllegalArgumentException;
	List<Address> getAddressList() throws IllegalArgumentException;

	Vehicle getVehicleDetails(int id) throws IllegalArgumentException;
	List<Vehicle> getVehicleList() throws IllegalArgumentException;


	List<Contact> getContactList() throws IllegalArgumentException;
	Contact getContactDetails(int id) throws IllegalArgumentException;
}