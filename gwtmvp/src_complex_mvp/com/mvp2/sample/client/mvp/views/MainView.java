package com.mvp2.sample.client.mvp.views;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.client.mvp.presenters.MainViewPresenter;

public class MainView extends DockLayoutPanel implements MainViewPresenter.Display {

	private ScrollPanel north;
	private ScrollPanel south;
	private ScrollPanel east;
	private ScrollPanel west;
	private ScrollPanel center;
	// private MainViewPresenter presenter;

	public MainView(Unit unit) {
		super(unit);
		animate(500);
		north = new ScrollPanel(new HTML("<B>north</B><BR>" + getDummyHTML(10)));
		south = new ScrollPanel(new HTML("<B>south</B><BR>" + getDummyHTML(10)));
		east = new ScrollPanel(new HTML("<B>east</B><BR>" + getDummyHTML(10)));
		west = new ScrollPanel(new HTML("<B>west</B><BR>" + getDummyHTML(10)));
		center = new ScrollPanel(new HTML("<B>center</B><BR>" + getDummyHTML(100)));

		north.addStyleName("northPanel");
		south.addStyleName("southPanel");
		east.addStyleName("eastPanel");
		west.addStyleName("westPanel");
		center.addStyleName("centerPanel");

		addNorth(north, 100);
		addSouth(south, 50);
		addEast(east, 0);
		addWest(west, 200);
		add(center);

		setUpLayout();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	private String getDummyHTML(int i) {
		String text = "This widget will only work in standards mode, which requires that the HTML page in which it is run have an explicit";
		StringBuffer line = new StringBuffer();
		for (int j = 0; j < i; j++) {
			line.append(text).append("<BR>");
		}
		return line.toString();
	}

	@Override
	public Panel getNoth() {
		return north;
	}

	@Override
	public Panel getSouth() {
		return south;
	}

	@Override
	public Panel getEast() {
		return east;
	}

	@Override
	public Panel getWest() {
		return west;
	}

	@Override
	public Panel getCenterBody() {
		return center;
	}

	@Override
	public void setUpLayout() {
		north.setSize("100%", "100%");
		south.setSize("100%", "100%");
		east.setSize("100%", "100%");
		west.setSize("100%", "100%");
		center.setSize("100%", "100%");
		setWidgetSize(east, 0);
	}

	@Override
	public void setPresenter(MainViewPresenter p) {
		// this.presenter = p;
	}
}