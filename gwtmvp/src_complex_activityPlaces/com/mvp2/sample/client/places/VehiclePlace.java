package com.mvp2.sample.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class VehiclePlace extends Place {
	private String parameters;

	public VehiclePlace(String token) {
		this.parameters = token;
	}

	public String getParameters() {
		return parameters;
	}

	public static class Tokenizer implements PlaceTokenizer<VehiclePlace> {
		@Override
		public String getToken(VehiclePlace place) {
			return place.getParameters();
		}

		@Override
		public VehiclePlace getPlace(String token) {
			return new VehiclePlace(token);
		}
	}
}