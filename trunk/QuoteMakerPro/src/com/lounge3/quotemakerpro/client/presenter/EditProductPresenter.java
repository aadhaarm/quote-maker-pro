package com.lounge3.quotemakerpro.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.event.ProductEvent;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;

public class EditProductPresenter implements Presenter{  

	public interface Display {
		HasClickHandlers getSaveButton();
		HasClickHandlers getCancelButton();
		HasValue<String> getName();
		HasValue<String> getDescription();
		HasValue<String> getProductTitle();
		HasValue<String> getPrice();

		HasValue<String> getTxtMinQuantity();
		HasValue<String> getTxtMaxQuantity();
		ListBox getLbQuantityAlgo();
		HasValue<String> getTxtQualtityMultiOrManual();
		HasValue<String> getTxtQuantityUnit();
		RadioButton getRbSelectionBased();
		RadioButton getRbQuantityBased();

		List<String> getSelectedCategories();
		void setData(List<ElementTO> data);
		boolean validateForm();

		Widget asWidget();
	}

	private ElementTO productElement;
	private final FormServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo loginInfo = null;
	private String productName;

	public EditProductPresenter(FormServiceAsync rpcService, HandlerManager eventBus, Display display, LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.productElement = new ElementTO();
		this.display = display;
		bind();
	}

	public EditProductPresenter(FormServiceAsync rpcService, HandlerManager eventBus, Display display, String productName, LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		this.productName = productName;
		bind();

		rpcService.getElement(productName, new AsyncCallback<ElementTO>() {

			public void onSuccess(ElementTO result) {
				productElement = result;
				if(productElement != null) {
					EditProductPresenter.this.display.getName().setValue(productElement.getName());
					EditProductPresenter.this.display.getDescription().setValue(productElement.getDescription());
					EditProductPresenter.this.display.getProductTitle().setValue(productElement.getTitle());
					EditProductPresenter.this.display.getPrice().setValue(productElement.getPrice().toString());
				}
			}

			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);
			}
		});
	}

	public void bind() {

		this.display.getSaveButton().addClickHandler(new ClickHandler() {   
			public void onClick(ClickEvent event) {
				if(display.validateForm()) {
					doSave();
				}
			}
		});

		this.display.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get(Constants.DIV_MAIN_CONTENT).clear();
				eventBus.fireEvent(new ProductEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		fetchCategoriesAndSet();
		RootPanel.get(Constants.DIV_MAIN_CONTENT).clear();
		RootPanel.get(Constants.DIV_MAIN_CONTENT).add(display.asWidget());
	}

	private void fetchCategoriesAndSet() {
		rpcService.getElements(loginInfo.getScreenName(), "category", new AsyncCallback<List<ElementTO>>() {

			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);
			}

			@Override
			public void onSuccess(List<ElementTO> result) {
				display.setData(result);
			}
		});			
	}

	private void doSave() {
		this.productElement.setName(display.getName().getValue());
		this.productElement.setDescription(display.getDescription().getValue());
		this.productElement.setType("product");
		this.productElement.setTitle(display.getProductTitle().getValue());
		this.productElement.setPrice(Double.parseDouble(display.getPrice().getValue()));
		this.productElement.setQuantityAlgo(this.display.getLbQuantityAlgo().getValue(this.display.getLbQuantityAlgo().getSelectedIndex()));
		this.productElement.setQuantityUnit(this.display.getTxtQuantityUnit().getValue());
		this.productElement.setMultiOrMan(this.display.getTxtQualtityMultiOrManual().getValue());

		if(!this.display.getRbQuantityBased().isEnabled()) {
			this.productElement.setElementQuantityType(Constants.QUANTITY_TYPE_QUANTITY_BASED);
			this.productElement.setMinQuantity(new Long("1"));
			this.productElement.setMaxQuantity(new Long("1"));
		} else {
			this.productElement.setElementQuantityType(Constants.QUANTITY_TYPE_SELECTION_BASED);

			if(Constants.QUANTITY_ALGO_MANUAL.equalsIgnoreCase(this.productElement.getQuantityAlgo())) {
				if(this.productElement.getMultiOrMan() != null) {
					String [] quantity = this.productElement.getMultiOrMan().split(",");
					if(quantity != null && quantity.length > 0) {
						this.productElement.setMinQuantity(Long.parseLong(quantity[0]));
						this.productElement.setMaxQuantity(Long.parseLong(quantity[quantity.length - 1]));
					}
				}
			} else {
				this.productElement.setMinQuantity(Long.parseLong(this.display.getTxtMinQuantity().getValue()));
				this.productElement.setMaxQuantity(Long.parseLong(this.display.getTxtMaxQuantity().getValue()));
			}
		}


		rpcService.addNewElement(productElement, loginInfo.getScreenName(), display.getSelectedCategories(), new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);
			}

			@Override
			public void onSuccess(Void result) {
				RootPanel.get(Constants.DIV_MAIN_CONTENT).clear();
				eventBus.fireEvent(new ProductEvent());
			}
		});
	}

}