package com.mvp2.sample.client.mvp.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.mvp2.sample.client.mvp.activity.SouthViewPresenter;
import com.mvp2.sample.client.mvp.events.StatusEvent;
import com.mvp2.sample.client.mvp.events.StatusEventHandler;

public class SouthView extends Composite {
	private HTML html = new HTML("<B>Default South Message</B>");
	private EventBus eventBus;
	// private SouthViewPresenter presenter;
	private Button btnStop = new Button("Stop");
	private Button btnStart = new Button("Start");
	protected static HandlerRegistration handlerRegistration = null;

	private StatusEventHandler statusEventHandler = new StatusEventHandler() {
		@Override
		public void onShowStatus(StatusEvent event) {
			setMessage(event.getMessage());
		}
	};
	private HorizontalPanel hp = new HorizontalPanel();

	public SouthView(EventBus bus) {
		this.eventBus = bus;
		hp.add(btnStop);
		hp.add(btnStart);
		hp.add(new HTML("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"));
		btnStop.setStyleName("smallButton");
		btnStart.setStyleName("smallButton");
		hp.add(html);
		html.addStyleName("myStyle");

		initWidget(hp);
		handlerRegistration = eventBus.addHandler(StatusEvent.TYPE, statusEventHandler);
		bindEvents();
		toggleButtons();
	}

	public void setMessage(String text) {
		html.setHTML(text);
		hp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	}

	public Widget asWidget() {
		return this;
	}

	public void setPresenter(SouthViewPresenter presenter) {
		// this.presenter = presenter;
	}
	
	private void bindEvents() {
		btnStop.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				handlerRegistration.removeHandler();
				handlerRegistration = null;
				setMessage("Disabled Event Listning....");
				toggleButtons();
			}
		});
		btnStart.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				handlerRegistration = eventBus.addHandler(StatusEvent.TYPE, statusEventHandler);
				setMessage("Enabled Event Listning....");
				toggleButtons();
			}
		});
	}

	private void toggleButtons() {
		if(handlerRegistration == null) {
			btnStart.setVisible(true);
			btnStop.setVisible(false);
		} else {
			btnStart.setVisible(false);
			btnStop.setVisible(true);
		}
		
	}
}