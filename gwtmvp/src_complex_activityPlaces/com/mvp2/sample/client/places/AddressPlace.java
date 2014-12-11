package com.mvp2.sample.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AddressPlace extends Place {
	private String parameters;

	public AddressPlace(String token) {
		this.parameters = token;
	}

	public String getParameters() {
		return parameters;
	}

	public static class Tokenizer implements PlaceTokenizer<AddressPlace> {
		@Override
		public String getToken(AddressPlace place) {
			return place.getParameters();
		}

		@Override
		public AddressPlace getPlace(String token) {
			return new AddressPlace(token);
		}
	}
}