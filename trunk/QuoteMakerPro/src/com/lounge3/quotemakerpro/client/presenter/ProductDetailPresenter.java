package com.lounge3.quotemakerpro.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.event.AddCategoryEvent;
import com.lounge3.quotemakerpro.client.event.EditEvent;
import com.lounge3.quotemakerpro.client.event.ProductDetailEvent;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;

public class ProductDetailPresenter implements Presenter {  

	public interface Display {
		Widget asWidget();
		HasClickHandlers getCategoryList();
		HasClickHandlers getEditButton();
		HasClickHandlers getCancelButton();
		HasClickHandlers getProductTable();
		void setAssociatedCategoryData(List<ElementTO> data);
		String getClickedProduct(ClickEvent event);
		List<Integer> getSelectedRows();
		void setData(ElementTO data);
	}

	private final FormServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo loginInfo = null;
	private String selectedProductName;

	public ProductDetailPresenter(FormServiceAsync rpcService, HandlerManager eventBus, Display view, LoginInfo loginInfo, String selectedProductName) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loginInfo = loginInfo;
		this.selectedProductName = selectedProductName;
		bind();
	}

	private void bind() {
		display.getEditButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditEvent(selectedProductName));
			}
		});
		
		display.getCancelButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("detailDiv").clear();
				eventBus.fireEvent(new ProductDetailEvent(selectedProductName));
			}
		});
	}

	private void fetchAssociations() {
		rpcService.getAssociatedCategories(selectedProductName, new AsyncCallback<List<ElementTO>>() {
		
			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);
			}

			@Override
			public void onSuccess(List<ElementTO> result) {
				display.setAssociatedCategoryData(result);
			}
		});
	}
	
	private void fetchFormDetails() {
		rpcService.getElement(selectedProductName, new AsyncCallback<ElementTO>() {

			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);
			}

			@Override
			public void onSuccess(ElementTO result) {
				display.setData(result);
			}
		});
}

	@Override
	public void go(HasWidgets container) {
		fetchFormDetails();
		fetchAssociations();
		RootPanel.get("detailDiv").clear();
		RootPanel.get("detailDiv").add(display.asWidget());
	}
}