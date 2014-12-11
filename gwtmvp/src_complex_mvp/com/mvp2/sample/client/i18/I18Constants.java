package com.mvp2.sample.client.i18;

import com.google.gwt.i18n.client.Constants;

public interface I18Constants extends Constants {

	@DefaultStringValue("Contacts")
	String contactsButton();

	@DefaultStringValue("Vehicles")
	String vehiclesButton();

	@DefaultStringValue("Addresses")
	String addressesButton();

	@DefaultStringValue("Contact ID")
	String contactID();

	@DefaultStringValue("Contact Name")
	String contactName();

	@DefaultStringValue("English")
	String englishLang();

	@DefaultStringValue("Italian")
	String italianLang();

	@DefaultStringValue("Refresh")
	String refresh();

	@DefaultStringValue("Address Id")
	String addressId();

	@DefaultStringValue("Address City")
	String addressCity();

	@DefaultStringValue("Vehicle Id")
	String vehicleId();

	@DefaultStringValue("Vehicle Model")
	String vehicleModel();
}