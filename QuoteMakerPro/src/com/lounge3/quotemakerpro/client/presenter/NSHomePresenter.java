package com.lounge3.quotemakerpro.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;

public class NSHomePresenter implements Presenter {  

	public interface Display {
		Widget asWidget();
	}

	private final FormServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo loginInfo = null;

	public NSHomePresenter(FormServiceAsync rpcService, HandlerManager eventBus, Display view, LoginInfo loginInfo) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loginInfo = loginInfo;
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		RootPanel.get(Constants.DIV_MAIN_CONTENT).clear();
		RootPanel.get(Constants.DIV_MAIN_CONTENT).add(display.asWidget());
	}

	private void bind() {

	}
}