package com.mvp2.sample.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class ShowContactEvent extends GwtEvent<ShowContactEventHandler> {
	public static Type<ShowContactEventHandler> TYPE = new Type<ShowContactEventHandler>();
	private final int id;

	public ShowContactEvent(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}


	@Override
	public Type<ShowContactEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowContactEventHandler handler) {
		handler.onShowContact(this);
	}
}