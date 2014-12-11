package com.mvp2.sample.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class VehiclesPlace extends Place {
	private String parameters;

	public VehiclesPlace(String token) {
		this.parameters = token;
	}

	public String getParameters() {
		return parameters;
	}

	public static class Tokenizer implements PlaceTokenizer<VehiclesPlace> {
		@Override
		public String getToken(VehiclesPlace place) {
			return place.getParameters();
		}

		@Override
		public VehiclesPlace getPlace(String token) {
			return new VehiclesPlace(token);
		}
	}
}