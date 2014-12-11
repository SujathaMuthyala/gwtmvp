package com.mvp2.sample.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AddressesPlace extends Place {
	private String parameters;

	public AddressesPlace(String token) {
		this.parameters = token;
	}

	public String getParameters() {
		return parameters;
	}

	public static class Tokenizer implements PlaceTokenizer<AddressesPlace> {
		@Override
		public String getToken(AddressesPlace place) {
			return place.getParameters();
		}

		@Override
		public AddressesPlace getPlace(String token) {
			return new AddressesPlace(token);
		}
	}
}