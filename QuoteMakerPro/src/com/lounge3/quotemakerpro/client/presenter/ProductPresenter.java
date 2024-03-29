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
import com.lounge3.quotemakerpro.client.event.ProductDetailEvent;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;

public class ProductPresenter implements Presenter {  

	public interface Display {
		Widget asWidget();
		HasClickHandlers getCategoryList();
		HasClickHandlers getAddCategoryButton();
		HasClickHandlers getProductTable();
		void setData(List<ElementTO> data);
		String getClickedProduct(ClickEvent event);
		List<Integer> getSelectedRows();
	}

	private final FormServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo loginInfo = null;

	public ProductPresenter(FormServiceAsync rpcService, HandlerManager eventBus, Display view, LoginInfo loginInfo) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loginInfo = loginInfo;
		bind();
	}

	private void bind() {
		display.getAddCategoryButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddCategoryEvent());
			}
		});
		
		display.getProductTable().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String selectedProductName = display.getClickedProduct(event);
				eventBus.fireEvent(new ProductDetailEvent(selectedProductName));
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		fetchFormDetails();
		RootPanel.get(Constants.DIV_MAIN_CONTENT).clear();
		RootPanel.get(Constants.DIV_MAIN_CONTENT).add(display.asWidget());
	}

	private void fetchFormDetails() {
		rpcService.getElements(loginInfo.getScreenName(), "product", new AsyncCallback<List<ElementTO>>() {

			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);
			}

			@Override
			public void onSuccess(List<ElementTO> result) {
				display.setData(result);
			}
		});	
	}
}