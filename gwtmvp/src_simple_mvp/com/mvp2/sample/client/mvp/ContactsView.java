package com.mvp2.sample.client.mvp;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.shared.Contact;
import com.mvp2.sample.shared.ContactDetail;

public class ContactsView extends Composite implements ContactsViewPresenter.Display {

	private VerticalPanel table = new VerticalPanel();
	private Button btnBack = new Button("<--Back");
	private Button btnRefresh = new Button("Refresh");
	private Button btnReverse = new Button("Reverse");
	private Label contactDetailLabel = new Label();
	private ContactsViewPresenter presenter;
	private ArrayList<Label> labels = new ArrayList<Label>();

	public ContactsView() {
		VerticalPanel panel = new VerticalPanel();
		HorizontalPanel hp = new HorizontalPanel();
		panel.add(table);
		panel.add(contactDetailLabel);
		hp.add(btnRefresh);
		hp.add(btnReverse);
		hp.add(btnBack);
		panel.add(hp);
		initWidget(panel);
		bind();
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(List<Contact> contactList) {
		table.clear();
		if(contactList == null) {
			return;
		}
		int i = 0;
		for (Contact contact : contactList) {
			Label l = new Label("" + contact.getName() + "(" + contact.getId() + ")");
			labels.add(l);
			table.add(l);
			l.setTitle("" + contact.getId());
			i++;
		}
		for (final Label label : labels) {
			label.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					presenter.showContactDetails(label.getTitle());
				}
			});
		}
	}

	@Override
	public void setPresenter(ContactsViewPresenter presenter) {
		this.presenter = presenter;
	}

	private void bind() {
		btnRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.fetchContacts();
			}
		});

		btnReverse.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.fetchContactsReverse();
			}
		});
		
		btnBack.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.goBack();
			}
		});
	}

	@Override
	public void setContactDetail(ContactDetail contactDetail) {
		contactDetailLabel.setText("");
		if (contactDetail == null || contactDetail.getId() < 0) {
			return;
		}
		contactDetailLabel.setText(contactDetail.toString());
	}
}