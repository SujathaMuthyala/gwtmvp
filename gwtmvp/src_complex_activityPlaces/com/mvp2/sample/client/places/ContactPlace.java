package com.mvp2.sample.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ContactPlace extends Place {
	private String parameters;

	public ContactPlace(String token) {
		this.parameters = token;
	}

	public String getParameters() {
		return parameters;
	}

	public static class Tokenizer implements PlaceTokenizer<ContactPlace> {
		@Override
		public String getToken(ContactPlace place) {
			return place.getParameters();
		}

		@Override
		public ContactPlace getPlace(String token) {
			return new ContactPlace(token);
		}
	}
}