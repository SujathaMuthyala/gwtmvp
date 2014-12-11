package com.mvp2.sample.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class ShowContactsEvent extends GwtEvent<ShowContactsEventHandler> {
	  public static Type<ShowContactsEventHandler> TYPE = new Type<ShowContactsEventHandler>();
	  
	  @Override
	  public Type<ShowContactsEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(ShowContactsEventHandler handler) {
	    handler.onShowContacts(this);
	  }
}