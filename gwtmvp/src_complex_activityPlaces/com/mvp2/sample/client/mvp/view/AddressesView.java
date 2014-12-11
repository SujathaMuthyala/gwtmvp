package com.mvp2.sample.client.mvp.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.mvp2.sample.client.mvp.activity.AddressesViewPresenter;
import com.mvp2.sample.shared.Address;

public class AddressesView extends Composite {

	private CellTable<Address> table = new CellTable<Address>();
	private Button btnBack = new Button("<--Back");
	private Button btnRefresh = new Button("Refresh");
	private Button btnReverse = new Button("Reverse");
	private AddressesViewPresenter presenter;

	// private ListDataProvider<Address> dataProvider = new ListDataProvider<Address>();

	public static final ProvidesKey<Address> KEY_PROVIDER = new ProvidesKey<Address>() {
		@Override
		public Object getKey(Address item) {
			return item == null ? null : item.getId();
		}
	};

	public AddressesView() {
		VerticalPanel p = new VerticalPanel();
		initWidget(p);

		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<Address> idColumn = new TextColumn<Address>() {
			@Override
			public String getValue(Address object) {
				return "" + object.getId();
			}
		};
		table.addColumn(idColumn, "ID");
		TextColumn<Address> nameColumn = new TextColumn<Address>() {
			@Override
			public String getValue(Address object) {
				return object.getCity();
			}
		};
		table.addColumn(nameColumn, "City");
		table.setRowCount(0, true);
		table.setRowData(0, new ArrayList<Address>());

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Address> selectionModel = new SingleSelectionModel<Address>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Address selected = selectionModel.getSelectedObject();
				if (selected != null) {
					presenter.showAddress(selected.getId());
				}
			}
		});

		
		// dataProvider.addDataDisplay(table);

		table.setWidth("100%", true);
		table.setColumnWidth(idColumn, 35, Unit.PCT);
		table.setColumnWidth(nameColumn, 65, Unit.PCT);

		// SimplePager pager = new SimplePager();
		// pager.setDisplay(table);

		HorizontalPanel hp = new HorizontalPanel();
		hp.add(btnRefresh);
		hp.add(btnReverse);
		hp.add(btnBack);
		p.add(hp);

		p.add(table);
		VerticalPanel inside = new VerticalPanel();
		inside.add(table);
		p.add(inside);

		bindEvents();
	}

	public void setRefreshLabel(String text) {
		btnRefresh.setText(text);
	}

	public void setBackLabel(String text) {
		btnBack.setText(text);
	}

	public void setReverseLabel(String text) {
		btnReverse.setText(text);
	}

	private void bindEvents() {
		btnBack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.backToHome();
			}
		});
		btnRefresh.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.fetchAddresses();
			}
		});
		btnReverse.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.reverseAddressList();
			}
		});
	}

	public void setData(List<Address> result) {
		/*
		table.setRowCount(0);
		table.setRowCount(result.size(), true);
		table.setVisibleRange(0, result.size());
		table.setRowData(0, result);
		*/
		// dataProvider.getList().clear();
		// dataProvider.getList().addAll(result);
		// dataProvider.addDataDisplay(table);

		table.setVisibleRange(0, result.size());
		// Set the total row count. This isn't strictly necessary, but it
		// affects
		// paging calculations, so its good habit to keep the row count up to
		// date.
		table.setRowCount(result.size(), false);
		// Push the data into the widget.
		table.setRowData(0, result);
		// Add it to the root panel.

	}

	public Widget asWidget() {
		return this;
	}

	public void setPresenter(AddressesViewPresenter presenter) {
		this.presenter = presenter;
	}
}