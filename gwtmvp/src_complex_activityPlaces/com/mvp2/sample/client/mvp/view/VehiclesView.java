package com.mvp2.sample.client.mvp.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.mvp2.sample.client.mvp.activity.VehiclesViewPresenter;
import com.mvp2.sample.shared.Vehicle;

public class VehiclesView extends Composite {

	private CellTable<Vehicle> table = new CellTable<Vehicle>();
	private Button btnBack = new Button("<--Back");
	private Button btnRefresh = new Button("Refresh");
	private Button btnReverse = new Button("Reverse");
	private VehiclesViewPresenter presenter;

	// private ListDataProvider<Vehicle> dataProvider = new ListDataProvider<Vehicle>();

	public static final ProvidesKey<Vehicle> KEY_PROVIDER = new ProvidesKey<Vehicle>() {
		@Override
		public Object getKey(Vehicle item) {
			return item == null ? null : item.getId();
		}
	};

	public VehiclesView() {
		VerticalPanel p = new VerticalPanel();
		initWidget(p);

		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<Vehicle> idColumn = new TextColumn<Vehicle>() {
			@Override
			public String getValue(Vehicle object) {
				return "" + object.getId();
			}
		};
		table.addColumn(idColumn, "ID");
		TextColumn<Vehicle> nameColumn = new TextColumn<Vehicle>() {
			@Override
			public String getValue(Vehicle object) {
				return object.getModel();
			}
		};
		table.addColumn(nameColumn, "Model");
		table.setRowCount(0, true);
		table.setRowData(0, new ArrayList<Vehicle>());

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Vehicle> selectionModel = new SingleSelectionModel<Vehicle>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Vehicle selected = selectionModel.getSelectedObject();
				if (selected != null) {
					presenter.showVehicle(selected.getId());
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
				presenter.fetchVehicles();
			}
		});
		btnReverse.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				presenter.reverseVehicleList();
			}
		});
	}

	public void setData(List<Vehicle> result) {
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

	public void setPresenter(VehiclesViewPresenter presenter) {
		this.presenter = presenter;
	}
}