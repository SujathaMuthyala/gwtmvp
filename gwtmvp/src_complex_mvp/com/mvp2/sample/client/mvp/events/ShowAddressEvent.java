package com.mvp2.sample.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class ShowAddressEvent extends GwtEvent<ShowAddressEventHandler> {
	public static Type<ShowAddressEventHandler> TYPE = new Type<ShowAddressEventHandler>();
	private final int id;

	public ShowAddressEvent(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}



	@Override
	public Type<ShowAddressEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowAddressEventHandler handler) {
		handler.onShowAddress(this);
	}
}