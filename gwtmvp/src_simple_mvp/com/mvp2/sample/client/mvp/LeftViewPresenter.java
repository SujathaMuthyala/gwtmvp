package com.mvp2.sample.client.mvp;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvp2.sample.client.HistoryManager;
import com.mvp2.sample.client.factory.ClientFactory;

public class LeftViewPresenter implements Presenter {
	public interface Display extends IsWidget {
		public void setPresenter(LeftViewPresenter presenter);
	}

	private Display view;

	public LeftViewPresenter(ClientFactory factory, Display view) {
		this.view = view;
		bind();
	}

	@Override
	public void go(HasWidgets parent) {
		parent.clear();
		parent.add(view.asWidget());
	}

	public void showContacts() {
		History.newItem(HistoryManager.CONTACTS);
	}

	@Override
	public void bind() {
		view.setPresenter(this);
	}
}