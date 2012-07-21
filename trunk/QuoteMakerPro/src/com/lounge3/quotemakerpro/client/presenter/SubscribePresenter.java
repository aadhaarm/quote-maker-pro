package com.lounge3.quotemakerpro.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.client.util.ClientUtils;
import com.lounge3.quotemakerpro.shared.LoginInfo;

public class SubscribePresenter implements Presenter {  

	public interface Display {
		Widget asWidget();
		HasClickHandlers getCheckAvailabilityButton();
		HasClickHandlers getSubscribeButton();
		String getScreenNameText();
	}

	private final FormServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo loginInfo = null;

	public SubscribePresenter(FormServiceAsync rpcService, HandlerManager eventBus, Display view, LoginInfo loginInfo) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loginInfo = loginInfo;
		bind();
	}

	private void bind() {
		this.display.getSubscribeButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ClientUtils.subscribeUser(rpcService, eventBus, loginInfo, display.getScreenNameText());
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		RootPanel.get("bodyDiv").clear();
		RootPanel.get("bodyDiv").add(display.asWidget());
	}
}