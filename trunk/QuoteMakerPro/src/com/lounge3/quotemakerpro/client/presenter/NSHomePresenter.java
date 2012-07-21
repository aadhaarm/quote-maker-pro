package com.lounge3.quotemakerpro.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.event.LoginEvent;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;

public class NSHomePresenter implements Presenter {  

	public interface Display {
		Widget asWidget();
		HasClickHandlers getLoginLink();
		void setLoginLink(String loginLink);
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
		container.clear();
		container.add(display.asWidget());
	}

	private void bind() {
		if(loginInfo != null) {
			this.display.setLoginLink(loginInfo.getLoginUrl());
		}

		display.getLoginLink().addClickHandler(new ClickHandler() {   
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginEvent());
			}
		});
	}
}