package com.mvp2.sample.client.mvp.view;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.client.mvp.activity.ContactsViewPresenter;
import com.mvp2.sample.shared.Contact;

public class ContactsView extends Composite {

	private VerticalPanel table = new VerticalPanel();
	private Button btnBack = new Button("<--Back");
	private Button btnRefresh = new Button("Refresh");
	private Button btnReverse = new Button("Reverse");
	private Label contactDetailLabel = new Label();
	private ContactsViewPresenter presenter;

	public ContactsView() {
		VerticalPanel panel = new VerticalPanel();
		panel.setSize("100%", "100%");
		panel.addStyleName("eastPanel");
		HorizontalPanel hp = new HorizontalPanel();
		panel.add(table);
		panel.add(contactDetailLabel);
		hp.add(btnRefresh);
		hp.add(btnReverse);
		hp.add(btnBack);
		panel.add(hp);
		initWidget(panel);
		bindEvents();
	}

	public void setRefreshLabel(String text) {
		btnRefresh.setText(text);
	}
	public void setBackLabel(String text) {
		btnBack.setText(text);
	}
	public void setReverseLabel(String text) {
		btnReverse.setText(text);
	}

	private void bindEvents() {
		btnBack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.backToHome();
			}
		});
		btnRefresh.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.fetchContacts();
			}
		});
		btnReverse.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.reverseContactList();
			}
		});
	}

	public void setData(List<Contact> result) {
		table.clear();
		if(result == null || result.size() == 0) {
			return;
		}
		for(Contact contact : result) {
			table.add(new Label(contact.getId() + " ---- "+ contact.getName()));
		}
	}

	public Widget asWidget() {
		return this;
	}

	public void setPresenter(ContactsViewPresenter presenter) {
		this.presenter = presenter;
	}
}