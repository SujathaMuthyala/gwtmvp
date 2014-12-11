package com.mvp2.sample.client.mvp;

import java.util.Collections;
import java.util.List;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvp2.sample.client.HistoryManager;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.shared.Contact;
import com.mvp2.sample.shared.ContactDetail;

public class ContactsViewPresenter implements Presenter {

	private Display view;
	private ClientFactory factory;

	public interface Display extends IsWidget {
		public void setData(List<Contact> contactList);
		public void setContactDetail(ContactDetail contactDetail);
		public void setPresenter(ContactsViewPresenter presenter);
	}

	public ContactsViewPresenter(ClientFactory factory, Display view) {
		this.factory = factory;
		this.view = view;
		bind();
	}

	@Override
	public void bind() {
		view.setPresenter(this);
	}

	@Override
	public void go(HasWidgets parent) {
		parent.clear();
		view.setContactDetail(null);
		view.setData(null);
		fetchContacts();
		parent.add(view.asWidget());
	}

	public void fetchContacts() {
		factory.getGreetService().getContactList(new AsyncCallback<List<Contact>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Contact List");
			}

			@Override
			public void onSuccess(List<Contact> result) {
				System.out.println("ContactsViewPresenter fetched contacts ......");
				view.setContactDetail(null);
				view.setData(result);
			}
		});
	}

	public void fetchContactsReverse() {
		factory.getGreetService().getContactList(new AsyncCallback<List<Contact>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Contact List");
			}

			@Override
			public void onSuccess(List<Contact> result) {
				System.out.println("ContactsViewPresenter fetched contacts ......");
				view.setContactDetail(new ContactDetail());
				Collections.sort(result);
				Collections.reverse(result);
				view.setData(result);
			}
		});
	}

	public void showContactDetails(String contactId) {
		factory.getGreetService().getContactDetail(contactId, new AsyncCallback<ContactDetail>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Contact List");
			}

			@Override
			public void onSuccess(ContactDetail result) {
				System.out.println("ContactsViewPresenter fetched ContactDetails ......");
				view.setContactDetail(result);
			}
		});
	}

	public void goBack() {
		History.newItem(HistoryManager.HOME);
	}
}