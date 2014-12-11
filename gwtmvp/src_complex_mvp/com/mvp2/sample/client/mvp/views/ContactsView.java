package com.mvp2.sample.client.mvp.views;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.client.mvp.presenters.ContactsViewPresenter;
import com.mvp2.sample.shared.Contact;

public class ContactsView extends Composite implements ContactsViewPresenter.Display {
	private VerticalPanel table;
	private ContactsViewPresenter presenter;

	private Button refreshButton;

	public ContactsView() {
		VerticalPanel panel = new VerticalPanel();
		table = new VerticalPanel();
		table.setBorderWidth(2);
		refreshButton = new Button();
		panel.add(refreshButton);
		panel.add(table);
		initWidget(panel);
		bind();
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(List<Contact> list) {
		table.clear();
		if(list == null) {
			return;
		}
		for (final Contact entity : list) {
			Label l = new Label("" + entity.getName() + "(" + entity.getId() + ")");

			l.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					presenter.showContact(entity.getId());
				}
			});
			table.add(l);
			l.setTitle("" + entity.getId());
		}
	}
	
	@Override
	public void setRefreshButtonLabel(String refreshButtonLabel) {
		this.refreshButton.setText(refreshButtonLabel);
	}

	private void bind() {
		refreshButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.fetchContacts();
			}
		});
	}

	@Override
	public void setPresenter(ContactsViewPresenter p) {
		presenter = p;
	}
}