package com.lounge3.quotemakerpro.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.lounge3.quotemakerpro.client.event.AddCategoryEvent;
import com.lounge3.quotemakerpro.client.event.AddCategoryEventHandler;
import com.lounge3.quotemakerpro.client.event.AddProductEvent;
import com.lounge3.quotemakerpro.client.event.AddProductEventHandler;
import com.lounge3.quotemakerpro.client.event.CategoryEvent;
import com.lounge3.quotemakerpro.client.event.CategoryEventHandler;
import com.lounge3.quotemakerpro.client.event.EditEvent;
import com.lounge3.quotemakerpro.client.event.EditEventHandler;
import com.lounge3.quotemakerpro.client.event.EditFormEvent;
import com.lounge3.quotemakerpro.client.event.EditFormEventHandler;
import com.lounge3.quotemakerpro.client.event.FormEvent;
import com.lounge3.quotemakerpro.client.event.FormEventHandler;
import com.lounge3.quotemakerpro.client.event.LoginEvent;
import com.lounge3.quotemakerpro.client.event.LoginEventHandler;
import com.lounge3.quotemakerpro.client.event.LogoutEvent;
import com.lounge3.quotemakerpro.client.event.LogoutEventHandler;
import com.lounge3.quotemakerpro.client.event.ProductDetailEvent;
import com.lounge3.quotemakerpro.client.event.ProductDetailEventHandler;
import com.lounge3.quotemakerpro.client.event.ProductEvent;
import com.lounge3.quotemakerpro.client.event.ProductEventHandler;
import com.lounge3.quotemakerpro.client.event.UpdatedCategoryEvent;
import com.lounge3.quotemakerpro.client.event.UpdatedCategoryEventHandler;
import com.lounge3.quotemakerpro.client.event.UpdatedProductEvent;
import com.lounge3.quotemakerpro.client.event.UpdatedProductEventHandler;
import com.lounge3.quotemakerpro.client.presenter.CategoryPresenter;
import com.lounge3.quotemakerpro.client.presenter.EditCategoryPresenter;
import com.lounge3.quotemakerpro.client.presenter.EditFormPresenter;
import com.lounge3.quotemakerpro.client.presenter.EditProductPresenter;
import com.lounge3.quotemakerpro.client.presenter.FormPresenter;
import com.lounge3.quotemakerpro.client.presenter.FormUserPresenter;
import com.lounge3.quotemakerpro.client.presenter.MenuPresenter;
import com.lounge3.quotemakerpro.client.presenter.Presenter;
import com.lounge3.quotemakerpro.client.presenter.ProductDetailPresenter;
import com.lounge3.quotemakerpro.client.presenter.ProductPresenter;
import com.lounge3.quotemakerpro.client.presenter.StatusBarPresenter;
import com.lounge3.quotemakerpro.client.presenter.SubscribePresenter;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.client.util.ClientUtils;
import com.lounge3.quotemakerpro.client.view.CategoryView;
import com.lounge3.quotemakerpro.client.view.EditCategoryView;
import com.lounge3.quotemakerpro.client.view.EditFormView;
import com.lounge3.quotemakerpro.client.view.EditProductView;
import com.lounge3.quotemakerpro.client.view.FormUserView;
import com.lounge3.quotemakerpro.client.view.FormView;
import com.lounge3.quotemakerpro.client.view.MenuView;
import com.lounge3.quotemakerpro.client.view.ProductDetailView;
import com.lounge3.quotemakerpro.client.view.ProductView;
import com.lounge3.quotemakerpro.client.view.StatusBarView;
import com.lounge3.quotemakerpro.client.view.SubscribeView;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;

public class QuoteController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final FormServiceAsync rpcService; 
	private HasWidgets container;
	private LoginInfo loginInfo = null;

	private String selectedProductName;	

	public QuoteController(FormServiceAsync rpcService, HandlerManager eventBus, LoginInfo loginInfo) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.loginInfo = loginInfo;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
			@Override
			public void onLoginLinkClicked(LoginEvent event) {
				History.newItem("login");			}
		});

		eventBus.addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {
			@Override
			public void onLogoutLinkClicked(LogoutEvent event) {
				History.newItem("logout");
			}
		});

		eventBus.addHandler(CategoryEvent.TYPE, new CategoryEventHandler() {
			@Override
			public void onAddFormClicked(CategoryEvent event) {
				History.newItem(Constants.HISTORYITEM_LIST_CATEGORY);
			}
		});

		eventBus.addHandler(UpdatedCategoryEvent.TYPE, new UpdatedCategoryEventHandler() {
			@Override
			public void onFormUpdationClicked(UpdatedCategoryEvent event) {
				History.newItem(Constants.HISTORYITEM_LIST_CATEGORY);	
			}
		});

		eventBus.addHandler(AddCategoryEvent.TYPE, new AddCategoryEventHandler() {

			@Override
			public void onAddFormClicked(AddCategoryEvent event) {
				History.newItem("AddCategory");
			}
		});

		eventBus.addHandler(AddProductEvent.TYPE, new AddProductEventHandler() {

			@Override
			public void onAddProductClicked(AddProductEvent event) {
				History.newItem("AddProduct");
			}
		});

		eventBus.addHandler(ProductEvent.TYPE, new ProductEventHandler() {

			@Override
			public void onAddProductClicked(ProductEvent event) {
				History.newItem("ListProducts");
			}
		});

		eventBus.addHandler(UpdatedProductEvent.TYPE, new UpdatedProductEventHandler() {

			@Override
			public void onProductUpdate(UpdatedProductEvent event) {
				History.newItem("ListProducts");
			}
		});

		eventBus.addHandler(ProductDetailEvent.TYPE, new ProductDetailEventHandler() {

			@Override
			public void onShowProductDetail(ProductDetailEvent event) {
				selectedProductName = event.getProductName();
				History.newItem("ProductDetail");

			}
		});

		eventBus.addHandler(EditEvent.TYPE, new EditEventHandler() {

			@Override
			public void onFormRowClicked(EditEvent event) {
				History.newItem("EditProduct");
			}
		});

		eventBus.addHandler(EditFormEvent.TYPE, new EditFormEventHandler() {

			@Override
			public void onFormRowClicked(EditFormEvent event) {
				History.newItem(Constants.HISTORYITEM_EDIT_FORM);
			}
		});

		eventBus.addHandler(FormEvent.TYPE, new FormEventHandler() {

			@Override
			public void onAddFormClicked(FormEvent event) {
				History.newItem(Constants.HISTORYITEM_LIST_FORM);
			}
		});
	}

	public void go(final HasWidgets container) {
		this.container = container;
		
		// Check for subscribed user
		ClientUtils.checkSubscribed(loginInfo, rpcService);
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;
			//			presenter = new StatusBarPresenter(rpcService, eventBus, new StatusBarView(), loginInfo);
			//			presenter.go(container);
			//			presenter = new MenuPresenter(rpcService, eventBus, new MenuView(), loginInfo);
			//			presenter.go(container);
			//			presenter = null;

			if (token.equals("logout")) {
				presenter = new MenuPresenter(rpcService, eventBus, new MenuView(), loginInfo);
				presenter.go(container);
				presenter = new StatusBarPresenter(rpcService, eventBus, new StatusBarView(), loginInfo);
				presenter.go(container);
				presenter = null;
			} else if (token.equals("login")) {
				presenter = new MenuPresenter(rpcService, eventBus, new MenuView(), loginInfo);
				presenter.go(container);
				presenter = new StatusBarPresenter(rpcService, eventBus, new StatusBarView(), loginInfo);
				presenter.go(container);
				presenter = null;
			} else if (token.equals(Constants.HISTORYITEM_LIST_CATEGORY)) {
				presenter = new CategoryPresenter(rpcService, eventBus, new CategoryView(), loginInfo);
			} else if (token.equals("AddCategory")) {
				presenter = new EditCategoryPresenter(rpcService, eventBus, new EditCategoryView(), loginInfo);
			} else if (token.equals("AddProduct")) {
				presenter = new EditProductPresenter(rpcService, eventBus, new EditProductView(), loginInfo);
			} else if (token.equals("ListProducts")) {
				presenter = new ProductPresenter(rpcService, eventBus, new ProductView(), loginInfo);  
			} else if (token.equals("ProductDetail")) {
				presenter = new ProductDetailPresenter(rpcService, eventBus, new ProductDetailView(), loginInfo, selectedProductName);  
			} else if (token.equals("EditProduct")) {
				presenter = new EditProductPresenter(rpcService, eventBus, new EditProductView(), selectedProductName, loginInfo);  
			} else if (token.equals(Constants.HISTORYITEM_EDIT_FORM)) {
				presenter = new EditFormPresenter(rpcService, eventBus, new EditFormView(), loginInfo);  
			} else if (token.equals(Constants.HISTORYITEM_LIST_FORM)) {
				presenter = new FormPresenter(rpcService, eventBus, new FormView(), loginInfo);  
			} else if (token.equals(Constants.HISTORYITEM_SHOW_SAVED_FORM)) {
				String formSaveId = com.google.gwt.user.client.Window.Location.getParameter("formSaveId");
				String formName = com.google.gwt.user.client.Window.Location.getParameter("formName");
				presenter = new FormUserPresenter(rpcService, eventBus, formName, formSaveId, new FormUserView(), loginInfo);
			} else if (token.equals(Constants.HISTORYITEM_SUBSCRIBE)) {
				presenter = new SubscribePresenter(rpcService, eventBus, new SubscribeView(), loginInfo);  
			} else {
				presenter = new FormUserPresenter(rpcService, eventBus, token, new FormUserView(), loginInfo);
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}
}