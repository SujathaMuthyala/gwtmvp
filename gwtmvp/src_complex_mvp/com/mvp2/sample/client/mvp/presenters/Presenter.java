package com.mvp2.sample.client.mvp.presenters;

import com.google.gwt.user.client.ui.HasWidgets;

public interface Presenter {
	public void go(final HasWidgets parent);
	public void bind();
}
