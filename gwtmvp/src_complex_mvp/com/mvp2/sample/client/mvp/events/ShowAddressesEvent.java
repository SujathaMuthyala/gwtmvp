package com.mvp2.sample.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class ShowAddressesEvent extends GwtEvent<ShowAddressesEventHandler> {
	  public static Type<ShowAddressesEventHandler> TYPE = new Type<ShowAddressesEventHandler>();
	  
	  @Override
	  public Type<ShowAddressesEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(ShowAddressesEventHandler handler) {
	    handler.onShowAddresses(this);
	  }
}