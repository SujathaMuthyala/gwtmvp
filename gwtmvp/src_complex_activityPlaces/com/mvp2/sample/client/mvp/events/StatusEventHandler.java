package com.mvp2.sample.client.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface StatusEventHandler extends EventHandler {
	void onShowStatus(StatusEvent event);
}