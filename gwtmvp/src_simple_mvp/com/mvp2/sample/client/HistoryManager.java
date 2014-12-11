package com.mvp2.sample.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.mvp.ContactsView;
import com.mvp2.sample.client.mvp.ContactsViewPresenter;
import com.mvp2.sample.client.mvp.LeftView;
import com.mvp2.sample.client.mvp.LeftViewPresenter;
import com.mvp2.sample.client.mvp.Presenter;

public class HistoryManager implements ValueChangeHandler<String> {

	public static final String HOME = "HOME";
	public static final String CONTACTS = "CONTACTS";

	private static final ClientFactory factory = GWT.create(ClientFactory.class);

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String historyToken = event.getValue();
		if (historyToken != null && !historyToken.equals("")) {
			System.out.println(historyToken);
			if (historyToken.startsWith(HOME)) {
				showHome(historyToken);
			} else if (historyToken.startsWith(CONTACTS)) {
				showContacts(historyToken);
			}
		} else {
			History.newItem(HOME);
		}
	}

	private void showContacts(String historyToken) {
		GWT.runAsync(new RunAsyncCallback() {
			ContactsView view = factory.getContactsView();
			RootPanel.get().setStyleName("westPanel");
			Presenter p = new ContactsViewPresenter(factory, view);
			p.go(RootPanel.get());
		});
	}

	private void showHome(String historyToken) {
		GWT.runAsync(new RunAsyncCallback() {
			LeftView view = factory.getLeftView();
			RootPanel.get().setStyleName("northPanel");
			Presenter p = new LeftViewPresenter(factory, view);
			p.go(RootPanel.get());
		});
	}
}