package com.lounge3.quotemakerpro.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.lounge3.quotemakerpro.client.proxy.FormService;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class QuoteMakerPro implements EntryPoint {

	private LoginInfo loginInfo = null;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		FormServiceAsync quoteRpcService = GWT.create(FormService.class);
		HandlerManager quoteEventBus = new HandlerManager(null);
		checkUserLoginStatus(quoteRpcService, quoteEventBus);
	}

	private void checkUserLoginStatus(final FormServiceAsync quoteRpcService, final HandlerManager eventBus) {
		quoteRpcService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
				Window.alert(Constants.SERVER_ERROR);
			}
			public void onSuccess(LoginInfo loginInfo) {
				setLoginInfo(loginInfo);
				QuoteController quoteController = new QuoteController(quoteRpcService, eventBus, loginInfo);
				quoteController.go(RootPanel.get());
			}
		});
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
}