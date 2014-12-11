package com.mvp2.sample.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ContactsPlace extends Place {
	private String parameters;

	public ContactsPlace(String token) {
		this.parameters = token;
	}

	public String getParameters() {
		return parameters;
	}

	public static class Tokenizer implements PlaceTokenizer<ContactsPlace> {
		@Override
		public String getToken(ContactsPlace place) {
			return place.getParameters();
		}

		@Override
		public ContactsPlace getPlace(String token) {
			return new ContactsPlace(token);
		}
	}
}