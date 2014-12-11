package com.mvp2.sample.client.mvp;

import com.google.gwt.user.client.ui.HasWidgets;

public interface Presenter {
	// Method to pass Parent Widget into which associated view with this Presenter can be added.
	// Step 1 ---: Clear the Parent widget
	// Step 2 ---: Set the FormBeans/Models which is needed by the view.
	// ------------:Either New Models/FormBeans can be created for new Form or can be populated from AJAX calls (rpcService)
	// Step 3 ---: attached View to ParentWidget passed.
	public void go(final HasWidgets parent);

	// Associate view with current Presenter.
	// As a convention it is Important to :- call this from constructor.
	public void bind();
}