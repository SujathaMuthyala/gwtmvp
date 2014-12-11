package com.mvp2.sample.client.placemapper;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.mvp2.sample.client.places.AddressPlace;
import com.mvp2.sample.client.places.AddressesPlace;
import com.mvp2.sample.client.places.ContactPlace;
import com.mvp2.sample.client.places.ContactsPlace;
import com.mvp2.sample.client.places.LeftPlace;
import com.mvp2.sample.client.places.VehiclePlace;
import com.mvp2.sample.client.places.VehiclesPlace;

@WithTokenizers({ LeftPlace.Tokenizer.class, ContactsPlace.Tokenizer.class, ContactPlace.Tokenizer.class, AddressPlace.Tokenizer.class, AddressesPlace.Tokenizer.class, VehiclesPlace.Tokenizer.class, VehiclePlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}