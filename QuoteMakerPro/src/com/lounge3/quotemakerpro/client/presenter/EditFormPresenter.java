package com.lounge3.quotemakerpro.client.presenter;

import java.util.List;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.controller.ElementDragController;
import com.lounge3.quotemakerpro.client.controller.ElementDropController;
import com.lounge3.quotemakerpro.client.event.FormEvent;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;
import com.lounge3.quotemakerpro.shared.TO.FormTO;

public class EditFormPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		void setCategories(List<ElementTO> categoryList);

		String getFormName();

		String getFormTitle();

		String getFormDesc();

		Widget asWidget();

		Widget getFormTable();

		boolean setupDND(ElementDragController categoryDragController,
				ElementDropController elementDropController);
	}

	private final FormServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo loginInfo = null;
	List<ElementTO> categoryList;
	private FormTO formTO;

	public EditFormPresenter(FormServiceAsync rpcService,
			HandlerManager eventBus, Display display, LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		formTO = new FormTO();
		bind();
	}

	public EditFormPresenter(FormServiceAsync rpcService,
			HandlerManager eventBus, Display display, String productName,
			LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		// rpcService.getForm(productName, new AsyncCallback<ElementTO>() {
		//
		// public void onSuccess(ElementTO result) {
		// categoryElement = result;
		// if(categoryElement != null) {
		// EditFormPresenter.this.display.getName().setValue(categoryElement.getName());
		// EditFormPresenter.this.display.getDescription().setValue(categoryElement.getDescription());
		// EditFormPresenter.this.display.getProductTitle().setValue(categoryElement.getTitle());
		// EditFormPresenter.this.display.getPrice().setValue(categoryElement.getPrice().toString());
		// }
		// }
		//
		// public void onFailure(Throwable caught) {
		// Window.alert(Constants.SERVER_ERROR);
		// }
		// });
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
				RootPanel.get(Constants.DIV_MAIN_CONTENT).clear();
			}
		});
	}

	public void go(final HasWidgets container) {
		populateCategories();
		this.display.setupDND(new ElementDragController(RootPanel.get(), true),
				new ElementDropController(this.display.getFormTable(), formTO,
						rpcService, eventBus));

		// ensure the document BODY has dimensions in standards mode
		RootPanel.get().setPixelSize(600, 600);

		// create a DragController to manage drag-n-drop actions
		// note: This creates an implicit DropController for the boundary panel
		PickupDragController dragController = new PickupDragController(
				RootPanel.get(), true);

		// // add a new image to the boundary panel and make it draggable
		// Image img = new
		// Image("http://code.google.com/webtoolkit/logo-185x175.png");
		// RootPanel.get().add(img, 40, 30);
		// dragController.makeDraggable(img);

		RootPanel.get(Constants.DIV_MAIN_CONTENT).clear();
		RootPanel.get(Constants.DIV_MAIN_CONTENT).add(display.asWidget());
	}

	private void doSave() {
		formTO.setName(display.getFormName());
		formTO.setTitle(display.getFormTitle());
		formTO.setDescription(display.getFormDesc());

		rpcService.createForm(formTO, loginInfo.getScreenName(),
				new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						eventBus.fireEvent(new FormEvent());
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(Constants.SERVER_ERROR);

					}
				});
	}

	private void populateCategories() {
		rpcService.getElements(loginInfo.getScreenName(), "category",
				new AsyncCallback<List<ElementTO>>() {

					public void onFailure(Throwable caught) {
						Window.alert(Constants.SERVER_ERROR);
					}

					@Override
					public void onSuccess(List<ElementTO> result) {
						categoryList = result;
						display.setCategories(categoryList);
					}
				});
	}
}