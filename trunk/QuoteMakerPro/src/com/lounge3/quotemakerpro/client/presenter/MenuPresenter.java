package com.lounge3.quotemakerpro.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.event.AddCategoryEvent;
import com.lounge3.quotemakerpro.client.event.AddProductEvent;
import com.lounge3.quotemakerpro.client.event.CategoryEvent;
import com.lounge3.quotemakerpro.client.event.EditFormEvent;
import com.lounge3.quotemakerpro.client.event.FormEvent;
import com.lounge3.quotemakerpro.client.event.ProductEvent;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;

public class MenuPresenter implements Presenter{  

	public interface Display {
		HasClickHandlers getCategoryListButton();
		HasClickHandlers getProductListButton();
		HasClickHandlers getFormListButton();
		HasClickHandlers getAncAddCategoryLink();
		HasClickHandlers getAncAddProductLink();
		HasClickHandlers getAncAddFormLink();
		Widget asWidget();
	}

	private final FormServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo loginInfo = null;

	public MenuPresenter(FormServiceAsync rpcService, HandlerManager eventBus, Display display, LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();
	}

	public void bind() {
		this.display.getCategoryListButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CategoryEvent());
			}
		});
		
		this.display.getAncAddCategoryLink().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddCategoryEvent());
			}
		});
		
		this.display.getAncAddProductLink().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddProductEvent());	
			}
		});
		this.display.getProductListButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ProductEvent());
			}
		});
		this.display.getFormListButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new FormEvent());
			}
		});
		this.display.getAncAddFormLink().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditFormEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		if(loginInfo.isLoggedIn()) {		
			RootPanel.get(Constants.DIV_LEFT_MENU).clear();
			RootPanel.get(Constants.DIV_LEFT_MENU).add(display.asWidget());
		} else {
			RootPanel.get(Constants.DIV_LEFT_MENU).clear();
		}
	}
}