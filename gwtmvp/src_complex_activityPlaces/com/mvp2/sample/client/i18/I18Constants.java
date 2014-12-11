package com.mvp2.sample.client.i18;

import com.google.gwt.i18n.client.Constants;

public interface I18Constants extends Constants {
	@DefaultStringValue("English")
	String englishLang();

	@DefaultStringValue("Italian")
	String italianLang();

	@DefaultStringValue("Dummy content here")
	String content();





	@DefaultStringValue("<--Back")
	String backButton();

	@DefaultStringValue("Refresh")
	String refresh();

	@DefaultStringValue("Refresh")
	String refreshButton();

	@DefaultStringValue("Reverse")
	String reverseButton();





	@DefaultStringValue("Address Detail")
	String addressDetail();

	@DefaultStringValue("Address Id")
	String addressId();

	@DefaultStringValue("Address City")
	String addressCity();

	@DefaultStringValue("Addresses")
	String addressesButton();




	@DefaultStringValue("Contact Detail")
	String contactDetail();

	@DefaultStringValue("Contact ID")
	String contactID();

	@DefaultStringValue("Contact Name")
	String contactName();

	@DefaultStringValue("Contacts")
	String contactsButton();





	@DefaultStringValue("Vehicle Detail")
	String vehicleDetail();

	@DefaultStringValue("Vehicle Id")
	String vehicleId();

	@DefaultStringValue("Vehicle Model")
	String vehicleModel();

	@DefaultStringValue("Vehicles")
	String vehiclesButton();
}