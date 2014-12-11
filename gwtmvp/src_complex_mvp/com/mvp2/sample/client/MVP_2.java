package com.mvp2.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;

public class MVP_2 implements EntryPoint {
	public void onModuleLoad() {
		History.addValueChangeHandler(new HistoryManager());
		History.fireCurrentHistoryState();
		Window.addWindowClosingHandler(new ClosingHandler(){
			public void onWindowClosing(ClosingEvent event) {
				event.setMessage("Ran out of history. Now leaving application, is that OK?");
			}
		});
	}
}