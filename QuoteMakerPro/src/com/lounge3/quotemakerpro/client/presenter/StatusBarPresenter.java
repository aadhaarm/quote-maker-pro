package com.lounge3.quotemakerpro.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.event.LoginEvent;
import com.lounge3.quotemakerpro.client.event.LogoutEvent;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;

public class StatusBarPresenter implements Presenter{  
	
	public interface Display {
		HasClickHandlers getLoginLink();
		void setLoginLink(String loginLink);
		void setUserName(String userName);
		void setIsLoggedIn(boolean loginStatus);
		Widget asWidget();
	}

	private final FormServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo loginInfo = null;

	public StatusBarPresenter(FormServiceAsync rpcService, HandlerManager eventBus, Display display, LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		loginOrLogout();
		bind();
	}

	public void bind() {
		
		this.display.getLoginLink().addClickHandler(new ClickHandler() {   
			public void onClick(ClickEvent event) {
				if(loginInfo != null && loginInfo.isLoggedIn()) {
					eventBus.fireEvent(new LogoutEvent());
				} else {
					eventBus.fireEvent(new LoginEvent());
				}
				
			}
		});
	}
	
	private void loginOrLogout() {
		if(loginInfo != null && loginInfo.isLoggedIn()) {
			display.setIsLoggedIn(true);
			this.display.setLoginLink(loginInfo.getLogoutUrl());
			this.display.setUserName(loginInfo.getNickname());
		} else {
			display.setIsLoggedIn(false);
			this.display.setLoginLink(loginInfo.getLoginUrl());
			this.display.setUserName(null);
		}
	}

	public void go(final HasWidgets container) {
		RootPanel.get(Constants.DIV_RIGHT_STATUS).clear();
		RootPanel.get(Constants.DIV_RIGHT_STATUS).add(display.asWidget());
	}
}