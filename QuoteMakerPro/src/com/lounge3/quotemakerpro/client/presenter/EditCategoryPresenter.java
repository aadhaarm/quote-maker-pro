package com.lounge3.quotemakerpro.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.event.CategoryEvent;
import com.lounge3.quotemakerpro.client.event.UpdatedCategoryEvent;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;

public class EditCategoryPresenter implements Presenter{  
	public interface Display {
		HasClickHandlers getSaveButton();
		HasClickHandlers getCancelButton();
		HasValue<String> getName();
		HasValue<String> getDescription();
		HasValue<String> getCategoryTitle();
		Widget asWidget();
	}

	private ElementTO categoryElement;
	private final FormServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo loginInfo = null;

	public EditCategoryPresenter(FormServiceAsync rpcService, HandlerManager eventBus, Display display, LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.categoryElement = new ElementTO();
		this.display = display;
		bind();
	}

	public EditCategoryPresenter(FormServiceAsync rpcService, HandlerManager eventBus, Display display, String categoryName, LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		rpcService.getElement(categoryName, new AsyncCallback<ElementTO>() {

			public void onSuccess(ElementTO result) {
				categoryElement = result;
				if(categoryElement != null) {
					EditCategoryPresenter.this.display.getName().setValue(categoryElement.getName());
					EditCategoryPresenter.this.display.getDescription().setValue(categoryElement.getDescription());
				}
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error retrieving contact");
			}
		});
	}

	public void bind() {

		this.display.getSaveButton().addClickHandler(new ClickHandler() {   
			public void onClick(ClickEvent event) {
				doSave();
			}
		});
		
		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CategoryEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		RootPanel.get("bodyDiv").clear();
		RootPanel.get("bodyDiv").add(display.asWidget());
	}

	private void doSave() {
		this.categoryElement.setName(display.getName().getValue());
		this.categoryElement.setDescription(display.getDescription().getValue());
		this.categoryElement.setTitle(display.getCategoryTitle().getValue());
		this.categoryElement.setType("category");
		rpcService.addNewElement(categoryElement, loginInfo.getScreenName(), new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);
			}

			@Override
			public void onSuccess(Void result) {
				eventBus.fireEvent(new UpdatedCategoryEvent());
			}
		});
	}
}