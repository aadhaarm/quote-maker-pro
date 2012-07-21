package com.lounge3.quotemakerpro.client.presenter;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;
import com.lounge3.quotemakerpro.shared.TO.FormSaveTO;
import com.lounge3.quotemakerpro.shared.TO.FormTO;
import com.lounge3.quotemakerpro.shared.TO.ProductSaveTO;

public class FormUserPresenter implements Presenter {  

	public interface Display {
		Widget asWidget();
		void setFormData(FormTO result);
		HasClickHandlers getSaveButton();
		void setSelectedProductsObject(Map<String, ProductSaveTO> selectedProducts);
	}

	private final FormServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo loginInfo = null;
	private String formName;
	private FormTO formTO;
	private Map<String, ProductSaveTO> selectedProducts;

	public FormUserPresenter(FormServiceAsync rpcService, HandlerManager eventBus, String formName, Display view, LoginInfo loginInfo) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loginInfo = loginInfo;
		this.formName = formName;
		
		selectedProducts = new HashMap<String, ProductSaveTO>();
		display.setSelectedProductsObject(selectedProducts);
		
		bind();
		getForm();
	}

	public FormUserPresenter(FormServiceAsync rpcService, HandlerManager eventBus, String formName, String formSaveId, Display view, LoginInfo loginInfo) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loginInfo = loginInfo;
		this.formName = formName;

		rpcService.getSaveForm(formSaveId, new AsyncCallback<FormSaveTO>() {
			
			@Override
			public void onSuccess(FormSaveTO result) {
				display.setSelectedProductsObject(result.getSelectedProducts());
				getForm();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);
			}
		});
		
		bind();
	}

	private void bind() {
		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				doSaveForm();	
			}
		});
	}

	protected void doSaveForm() {
		FormSaveTO formSaveTO = new FormSaveTO();
		
		formSaveTO.setSelectedProducts(selectedProducts);
		formSaveTO.setFormId(formTO.getFormId().toString());
		formSaveTO.setState(Constants.FORM_STATE_SAVE);
		formSaveTO.setUserEmail(loginInfo.getScreenName());

		this.rpcService.saveForm(formSaveTO, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				RootPanel.get("bodyDiv").clear();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		RootPanel.get("bodyDiv").clear();
		RootPanel.get("bodyDiv").add(display.asWidget());
	}

	private void getForm() {
		rpcService.getForm(formName, new AsyncCallback<FormTO>() {

			@Override
			public void onSuccess(FormTO formTOresult) {
				formTO = formTOresult;
				display.setFormData(formTOresult);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);	
			}
		});
	}
}