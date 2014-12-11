package com.mvp2.sample.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LeftPlace extends Place {
	private String parameters;

	public LeftPlace(String token) {
		this.parameters = token;
	}

	public String getParameters() {
		return parameters;
	}

	public static class Tokenizer implements PlaceTokenizer<LeftPlace> {
		@Override
		public String getToken(LeftPlace place) {
			return place.getParameters();
		}

		@Override
		public LeftPlace getPlace(String token) {
			return new LeftPlace(token);
		}
	}
}