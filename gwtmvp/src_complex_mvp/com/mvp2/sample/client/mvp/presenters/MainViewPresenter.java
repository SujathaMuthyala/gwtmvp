package com.mvp2.sample.client.mvp.presenters;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.mvp2.sample.client.factory.ClientFactory;

public class MainViewPresenter implements Presenter {

	private Display view;
	// private ClientFactory factory;

	public interface Display extends IsWidget {
		public Panel getNoth();
		public Panel getSouth();
		public Panel getEast();
		public Panel getWest();
		public Panel getCenterBody();
		public void setUpLayout();
		public void setPresenter(MainViewPresenter p);
	}

	public MainViewPresenter(ClientFactory factory, Display view) {
		// this.factory = factory;
		this.view = view;
		bind();
	}

	@Override
	public void go(HasWidgets parent) {
		parent.clear();
		parent.add(view.asWidget());
	}

	@Override
	public void bind() {
		view.setPresenter(this);
	}
}