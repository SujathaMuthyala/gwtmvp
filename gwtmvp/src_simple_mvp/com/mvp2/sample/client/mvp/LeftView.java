package com.mvp2.sample.client.mvp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LeftView extends Composite implements LeftViewPresenter.Display {

	private Button btnContacts = new Button("Contacts here");
	private LeftViewPresenter presenter;

	public LeftView() {
		VerticalPanel panel = new VerticalPanel();
		panel.add(btnContacts);
		initWidget(panel);
		bind();
	}

	private void bind() {
		btnContacts.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				presenter.showContacts();
			}
		});
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(LeftViewPresenter presenter) {
		this.presenter = presenter;
	}
}