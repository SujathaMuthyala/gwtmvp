package com.mvp2.sample.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mvp2.sample.client.service.GreetingService;
import com.mvp2.sample.shared.Address;
import com.mvp2.sample.shared.Contact;
import com.mvp2.sample.shared.Vehicle;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	private static Map<Integer, Contact> pMap = new HashMap<Integer, Contact>();
	private static Map<Integer, Address> aMap = new HashMap<Integer, Address>();
	private static Map<Integer, Vehicle> vMap = new HashMap<Integer, Vehicle>();
	private static String [] pNames = {"Shirish", "Prasad", "Apurva", "Nikhil", "Vinayak", "Vineeta", "Nivedita", "Sachin", "Partha", "Kapil", "Krishna"};
	private static String [] aNames = {"Mumbai", "Pune", "Delhi", "Banglore", "Chennai", "Kolkata", "Nasik", "Aurangabad", "Kilhapur", "Sangli", "Latur"};
	private static String [] vNames = {"Maruti", "Suzuki", "Hyundai", "Yamaha", "HeroHonda", "Bajaj", "Kawasaki", "Nissan", "BMW", "Audi", "Chrysler"};

	static {
		int i = 0;
		for (int j = 0; j < 30; j++) {
			if((j % 10) == 0) i = 0;
			Contact p = new Contact(j, pNames[i]);
			pMap.put(j, p);
			Address a = new Address(j, aNames[i]);
			aMap.put(j, a);
			Vehicle v = new Vehicle(j, vNames[i]);
			vMap.put(j, v);
			i++;
		}
	}

	@Override
	public Contact getContactDetails(int id) throws IllegalArgumentException {
		System.out.println("Returning ContactDetails from Server");
		return pMap.get(id);
	}

	@Override
	public Address getAddressDetails(int id) throws IllegalArgumentException {
		System.out.println("Returning AddressDetails from Server");
		return aMap.get(id);
	}

	@Override
	public Vehicle getVehicleDetails(int id) throws IllegalArgumentException {
		System.out.println("Returning VehicleDetails from Server");
		return vMap.get(id);
	}

	@Override
	public List<Contact> getContactList() throws IllegalArgumentException {
		System.out.println("Returning ContactList from Server");
		List<Contact> list = new ArrayList<Contact>(pMap.values());
		return list;
	}

	@Override
	public List<Address> getAddressList() throws IllegalArgumentException {
		System.out.println("Returning AddressList from Server");
		List<Address> list = new ArrayList<Address>(aMap.values());
		return list;
	}

	@Override
	public List<Vehicle> getVehicleList() throws IllegalArgumentException {
		System.out.println("Returning VehicleList from Server");
		List<Vehicle> list = new ArrayList<Vehicle>(vMap.values());
		return list;
	}
}