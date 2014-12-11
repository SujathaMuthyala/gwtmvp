package com.mvp2.sample.client.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface ShowContactsEventHandler extends EventHandler {
	void onShowContacts(ShowContactsEvent event);
}