package com.mvp2.sample.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class ShowVehiclesEvent extends GwtEvent<ShowVehiclesEventHandler> {
	  public static Type<ShowVehiclesEventHandler> TYPE = new Type<ShowVehiclesEventHandler>();
	  
	  @Override
	  public Type<ShowVehiclesEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(ShowVehiclesEventHandler handler) {
	    handler.onShowVehicles(this);
	  }
}