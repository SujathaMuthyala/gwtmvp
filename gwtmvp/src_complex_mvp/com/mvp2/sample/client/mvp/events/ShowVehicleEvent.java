package com.mvp2.sample.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class ShowVehicleEvent extends GwtEvent<ShowVehicleEventHandler> {
	public static Type<ShowVehicleEventHandler> TYPE = new Type<ShowVehicleEventHandler>();
	private final int id;

	public ShowVehicleEvent(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public Type<ShowVehicleEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowVehicleEventHandler handler) {
		handler.onShowVehicle(this);
	}
}