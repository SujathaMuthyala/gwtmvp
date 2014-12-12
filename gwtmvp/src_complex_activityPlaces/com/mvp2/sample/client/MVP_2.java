package com.mvp2.sample.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.mvp2.sample.client.factory.ClientFactory;
import com.mvp2.sample.client.placemapper.AppPlaceHistoryMapper;
import com.mvp2.sample.client.placemapper.EastContentActivityMapper;
import com.mvp2.sample.client.placemapper.LeftMenuActivityMapper;
import com.mvp2.sample.client.placemapper.MainContentActivityMapper;
import com.mvp2.sample.client.placemapper.NorthContentActivityMapper;
import com.mvp2.sample.client.placemapper.SouthContentActivityMapper;
import com.mvp2.sample.client.places.LeftPlace;

public class MVP_2 implements EntryPoint {
	private Logger logger = Logger.getLogger(MVP_2.class.getName());
	private final Place defaultPlace = new LeftPlace("World!");
	private final DockLayoutPanel rootContainer = new DockLayoutPanel(Unit.PX);
	private final DockLayoutPanel splitLayoutPanel = new DockLayoutPanel(Unit.PX);

	private final ScrollPanel leftMenu = new ScrollPanel();
	private final ScrollPanel mainContent = new ScrollPanel();

	private final ScrollPanel northContent = new ScrollPanel(new HTML("North"));
	private final ScrollPanel eastContent = new ScrollPanel(new HTML("East"));
	private final ScrollPanel southContent = new ScrollPanel(new HTML("South"));

	AcceptsOneWidget westDisplay = new AcceptsOneWidget() {
		int oldSize = 200; // TODO store real size dynamically

		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			if (widget == null) {
				splitLayoutPanel.setWidgetSize(leftMenu, 0);
			} else {
				splitLayoutPanel.setWidgetSize(leftMenu, oldSize);
			}
			leftMenu.setVisible(widget != null);
			leftMenu.setWidget(widget);
		}
	};

	AcceptsOneWidget centerDisplay = new AcceptsOneWidget() {
		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			// mainContent.setVisible(widget != null);
			mainContent.setWidget(widget);
		}
	};

	AcceptsOneWidget northDisplay = new AcceptsOneWidget() {
		int oldSize = 50; // TODO store real size dynamically

		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			if (widget == null) {
				splitLayoutPanel.setWidgetSize(northContent, 0);
			} else {
				splitLayoutPanel.setWidgetSize(northContent, oldSize);
			}
			northContent.setVisible(widget != null);
			northContent.setWidget(widget);
		}
	};

	AcceptsOneWidget southDisplay = new AcceptsOneWidget() {
		int oldSize = 50; // TODO store real size dynamically

		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			if (widget == null) {
				splitLayoutPanel.setWidgetSize(southContent, 0);
			} else {
				splitLayoutPanel.setWidgetSize(southContent, oldSize);
			}
			southContent.setVisible(widget != null);
			southContent.setWidget(widget);
		}
	};

	AcceptsOneWidget eastDisplay = new AcceptsOneWidget() {
		int oldSize = 200; // TODO store real size dynamically

		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			if (widget == null) {
				splitLayoutPanel.setWidgetSize(eastContent, 0);
			} else {
				splitLayoutPanel.setWidgetSize(eastContent, oldSize);
			}
			eastContent.setVisible(widget != null);
			eastContent.setWidget(widget);
		}
	};

	public void runApp() {
		splitLayoutPanel.addNorth(northContent, 0);
		splitLayoutPanel.addWest(leftMenu, 0);
		splitLayoutPanel.addEast(eastContent, 0);
		splitLayoutPanel.addSouth(southContent, 0);

		splitLayoutPanel.add(mainContent);

		rootContainer.add(splitLayoutPanel);

		leftMenu.addStyleName("northPanel");
		mainContent.addStyleName("centerPanel");

		southContent.addStyleName("southPanel");
		northContent.addStyleName("demoPanel1");
		eastContent.addStyleName("eastPanel");

		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper leftActivityMapper = new LeftMenuActivityMapper(clientFactory);
		ActivityManager leftActivityManager = new ActivityManager(leftActivityMapper, eventBus);
		leftActivityManager.setDisplay(westDisplay);

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper mainActivityMapper = new MainContentActivityMapper(clientFactory);
		ActivityManager mainActivityManager = new ActivityManager(mainActivityMapper, eventBus);
		mainActivityManager.setDisplay(centerDisplay);

		ActivityMapper southActivityMapper = new SouthContentActivityMapper(clientFactory);
		ActivityManager southActivityManager = new ActivityManager(southActivityMapper, eventBus);
		southActivityManager.setDisplay(southDisplay);

		ActivityMapper northActivityMapper = new NorthContentActivityMapper(clientFactory);
		ActivityManager northActivityManager = new ActivityManager(northActivityMapper, eventBus);
		northActivityManager.setDisplay(northDisplay);

		ActivityMapper eastActivityMapper = new EastContentActivityMapper(clientFactory);
		ActivityManager eastActivityManager = new ActivityManager(eastActivityMapper, eventBus);
		eastActivityManager.setDisplay(eastDisplay);

		/*
		 * ActivityMapper northActivityMapper = new
		 * NorthContentActivityMapper(clientFactory); ActivityManager
		 * northActivityManager = new ActivityManager(northActivityMapper,
		 * eventBus); northActivityManager.setDisplay(northDisplay);
		 * 
		 * ActivityMapper eastActivityMapper = new
		 * EastContentActivityMapper(clientFactory); ActivityManager
		 * eastActivityManager = new ActivityManager(eastActivityMapper,
		 * eventBus); eastActivityManager.setDisplay(eastDisplay);
		 */

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootLayoutPanel.get().add(rootContainer);
		// Goes to the place represented on URL else default place
		historyHandler.handleCurrentHistory();

		/*
		 * Window.addWindowClosingHandler(new ClosingHandler() { public void
		 * onWindowClosing(ClosingEvent event) { event.setMessage(
		 * "Ran out of history.  Now leaving application, is that OK?"); } });
		 */
	}

	@Override
	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				Throwable unwrapped = unwrap(e);
				logger.log(Level.SEVERE, "Ex caught!", e);
				// OR
				// User GWT-RPC Call
				// myService.handleException(e);
			}

			public Throwable unwrap(Throwable e) {
				if (e instanceof UmbrellaException) {
					UmbrellaException ue = (UmbrellaException) e;
					if (ue.getCauses().size() == 1) {
						return unwrap(ue.getCauses().iterator().next());
					}
				}
				return e;
			}
		});

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				runApp();
			}
		});

	}
}