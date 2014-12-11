package com.mvp2.sample.client.mvp.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.mvp2.sample.client.mvp.activity.NorthViewPresenter;

public class NorthView extends Composite {
	private HTML html = new HTML("<B>Default North Message</B>");
	// private EventBus eventBus;
	// private NorthViewPresenter presenter;
	private Button btnItalian = new Button("Italian");
	private Button btnEnglish = new Button("English");
	private HorizontalPanel hp = new HorizontalPanel();

	public NorthView(EventBus bus) {
		// this.eventBus = bus;
		hp.add(btnEnglish);
		hp.add(btnItalian);
		btnEnglish.setStyleName("smallButton");
		btnItalian.setStyleName("smallButton");

		hp.add(html);

		initWidget(hp);
		bindEvents();
	}

	public Widget asWidget() {
		return this;
	}

	public void setPresenter(NorthViewPresenter presenter) {
		// this.presenter = presenter;
	}
	
	private void bindEvents() {
		btnEnglish.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.Location.assign( // or replace()
						   Window.Location.createUrlBuilder()
						      .setParameter(LocaleInfo.getLocaleQueryParam(), "en")
						      .buildString());
			}
		});
		btnItalian.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.Location.assign( // or replace()
						   Window.Location.createUrlBuilder()
						      .setParameter(LocaleInfo.getLocaleQueryParam(), "it")
						      .buildString());
			}
		});
	}
}