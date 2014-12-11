package com.mvp2.sample.client.mvp.presenters;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.shared.Contact;

public class ContactViewPresenter implements Presenter {

	private Display view;
	private ClientFactory factory;
	private Contact contact;

	public interface Display extends IsWidget {
		public void setContactName(String contactName);
		public void setPresenter(ContactViewPresenter presenter);
	}

	public ContactViewPresenter(ClientFactory factory, Display view, Contact contact) {
		this.factory = factory;
		this.view = view;
		this.contact = contact;
		if(this.contact == null) {
			this.contact = new Contact();
		}
		bind();
	}

	@Override
	public void go(HasWidgets parent) {
		parent.clear();
		parent.add(view.asWidget());
		fetchContactDetails();
	}

	public void fetchContactDetails() {
		factory.getGreetService().getContactDetails(contact.getId(), new AsyncCallback<Contact>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Contact List");
			}

			@Override
			public void onSuccess(Contact result) {
				if(result != null) {
					contact = result;
					view.setContactName(contact.toString());
				} else {
					view.setContactName("There is no conatct with Contact ID : " + contact.getId());
				}
			}
		});
	}

	@Override
	public void bind() {
		// As there are  no events happening on the View, 
		// there is no need to have this method or below call.
		view.setPresenter(this);
	}
}