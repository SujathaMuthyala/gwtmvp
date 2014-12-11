package com.mvp2.sample.client.mvp.presenters;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.events.ShowContactEvent;
import com.mvp2.sample.shared.Contact;

public class ContactsViewPresenter implements Presenter {

	private Display view;
	private ClientFactory factory;
//	private List<Contact> contactList;

	public interface Display extends IsWidget {
		public void setData(List<Contact> contactList);
		public void setRefreshButtonLabel(String RefreshButtonLabel);
		public void setPresenter(ContactsViewPresenter p);
	}

	public ContactsViewPresenter(ClientFactory factory, Display view) {
		this.factory = factory;
		this.view = view;
		bind();
	}

	@Override
	public void go(HasWidgets parent) {
		parent.clear();
		fetchContacts();
		this.view.setRefreshButtonLabel(factory.getI18Constants().refresh());
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
				// contactList = result;
				view.setData(result);
			}
		});
	}

	public void showContact(int id) {
		factory.getEventBus().fireEvent(new ShowContactEvent(id));
	}

	@Override
	public void bind() {
		view.setPresenter(this);
	}
}