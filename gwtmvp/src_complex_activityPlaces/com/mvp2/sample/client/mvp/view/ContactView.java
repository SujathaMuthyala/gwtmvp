package com.mvp2.sample.client.mvp.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp2.sample.client.mvp.activity.ContactViewPresenter;

public class ContactView extends Composite {

	private HTML contactDetailLabel = new HTML();
	private HTML contactIdLabel = new HTML();
	private HTML contactCityLabel = new HTML();

	private Label contactIdValue = new Label();
	private Label contactCityValue = new Label();

	private Button btnBack = new Button("<--Back");

	private ContactViewPresenter presenter;

	public ContactView() {
		ScrollPanel main = new ScrollPanel();
		VerticalPanel panel = new VerticalPanel();
		panel.setSize("100%", "100%");

		FlexTable flexTable = new FlexTable();
		flexTable.setBorderWidth(2);
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(3);
		flexTable.setWidget(0, 0, contactDetailLabel);
		cellFormatter.setColSpan(0, 0, 2);

		flexTable.setWidget(1, 0, contactIdLabel);
		flexTable.setWidget(2, 0, contactCityLabel);

		flexTable.setWidget(1, 1, contactIdValue);
		flexTable.setWidget(2, 1, contactCityValue);

		flexTable.setWidget(3, 0, btnBack);
		cellFormatter.setColSpan(3, 0, 2);

		flexTable.setSize("60%",  "100%");
		flexTable.getColumnFormatter().setWidth(0, "50%");

		panel.add(flexTable);
		main.add(panel);
		initWidget(main);
		bindEvents();
	}

	public void setContactDetailLabel(String text) {
		contactDetailLabel.setHTML(text);
	}

	public void setBackLabel(String text) {
		btnBack.setText(text);
	}

	public void setContactIdLabel(String text) {
		contactIdLabel.setHTML(text);
	}

	public void setContactCityLabel(String text) {
		contactCityLabel.setHTML(text);
	}

	public void setContactIdValue(String text) {
		contactIdValue.setText(text);
	}

	public void setContactCityValue(String text) {
		contactCityValue.setText(text);
	}

	private void bindEvents() {
		btnBack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.backToContactList();
			}
		});
	}

	public Widget asWidget() {
		return this;
	}

	public void setPresenter(ContactViewPresenter presenter) {
		this.presenter = presenter;
	}
}