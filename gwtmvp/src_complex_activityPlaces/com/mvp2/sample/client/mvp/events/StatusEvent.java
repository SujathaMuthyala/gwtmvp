package com.mvp2.sample.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class StatusEvent extends GwtEvent<StatusEventHandler> {
	public static Type<StatusEventHandler> TYPE = new Type<StatusEventHandler>();
	private final String message;

	public StatusEvent(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public Type<StatusEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(StatusEventHandler handler) {
		handler.onShowStatus(this);
	}
}