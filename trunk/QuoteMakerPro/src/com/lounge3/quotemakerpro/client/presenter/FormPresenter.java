package com.lounge3.quotemakerpro.client.presenter;

import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;
import com.lounge3.quotemakerpro.shared.TO.FormTO;

public class FormPresenter implements Presenter {  

	public interface Display {
		Widget asWidget();
		void populateForms(List<FormTO> formTOs);
	}

	private final FormServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo loginInfo = null;

	public FormPresenter(FormServiceAsync rpcService, HandlerManager eventBus, Display view, LoginInfo loginInfo) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loginInfo = loginInfo;
		bind();
	}

	private void bind() {

	}

	@Override
	public void go(HasWidgets container) {
		fetchForms();
		RootPanel.get("bodyDiv").clear();
		RootPanel.get("bodyDiv").add(display.asWidget());
	}

	private void fetchForms() {
		rpcService.getforms(loginInfo.getScreenName(), new AsyncCallback<List<FormTO>>() {

			@Override
			public void onSuccess(List<FormTO> result) {
				display.populateForms(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);
			}
		});
	}

}