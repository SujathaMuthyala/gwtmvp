package com.mvp2.sample.client.mvp.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.mvp2.sample.client.mvp.activity.EastViewPresenter;

public class EastView extends Composite {
	private HTML html = new HTML("<B>Default East View</B>");
	// private EventBus eventBus;
	// private EastViewPresenter presenter;

	public EastView(EventBus bus) {
		// this.eventBus = bus;
		initWidget(html);
	}

	public Widget asWidget() {
		return this;
	}

	public void setPresenter(EastViewPresenter presenter) {
		// this.presenter = presenter;
	}
}