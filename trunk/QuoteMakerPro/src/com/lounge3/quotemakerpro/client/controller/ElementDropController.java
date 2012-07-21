package com.lounge3.quotemakerpro.client.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbstractDropController;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;
import com.lounge3.quotemakerpro.shared.TO.FormCategoryTO;
import com.lounge3.quotemakerpro.shared.TO.FormProductTO;
import com.lounge3.quotemakerpro.shared.TO.FormTO;

public class ElementDropController extends AbstractDropController {

	private FlexTable dropTarget;
	private FormServiceAsync rpcService;
	private HandlerManager eventBus;
	private FormTO formTO;

	public ElementDropController(Widget dropTarget, FormTO formTO, FormServiceAsync rpcService, HandlerManager eventBus) {
		super(dropTarget);
		this.dropTarget = (FlexTable)dropTarget;
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.formTO = formTO;
	}

	@Override
	public void onDrop(DragContext context) {
		super.onDrop(context);
		final Label lblCategoryName = (Label)context.draggable;

		// Get Category details from Server
		rpcService.getElement(lblCategoryName.getText(), new AsyncCallback<ElementTO>() {

			@Override
			public void onSuccess(ElementTO categoryTO) {

				//make a new category with ID
				final FormCategoryTO formCategoryTO = new FormCategoryTO(categoryTO.getId());

				if(categoryTO != null) {
					
					// Add category to the display table
					dropTarget.setWidget(dropTarget.getRowCount(), 0, lblCategoryName);

					// Get associated product list
					rpcService.getAssociatedProducts(categoryTO.getName(), new AsyncCallback<List<ElementTO>>() {

						// List of products in the category to be added into form
						List<FormProductTO> formProductTOs = null;

						@Override
						public void onSuccess(List<ElementTO> productTOs) {
							formProductTOs = new ArrayList<FormProductTO>();
							for (Iterator<ElementTO> iterator = productTOs.iterator(); iterator.hasNext();) {
								ElementTO product = (ElementTO) iterator.next();
								
								// Create product to be added to category to be added to form
								FormProductTO formProductTO = new FormProductTO(product.getId());
								// Add product to the product list
								formProductTOs.add(formProductTO);

								// Add products to display with one indent
								dropTarget.setWidget(dropTarget.getRowCount(), 1, new Label(product.getName()));
								dropTarget.setWidget(dropTarget.getRowCount() - 1, 2, new Label(product.getPrice().toString()));
							}
							// Add Product List to the category
							formCategoryTO.setProducts(formProductTOs);

							// Add category to the category list in the form
							getFormTO().getCategories().add(formCategoryTO);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(Constants.SERVER_ERROR);
						}
					});
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);

			}
		});
	}

	public FormTO getFormTO() {
		
		if(formTO == null) {
			formTO = new FormTO();
		}
		
		if(formTO.getCategories() == null) {
			formTO.setCategories(new ArrayList<FormCategoryTO>());
		}
		return formTO;
	}

	public void setFormTO(FormTO formTO) {
		this.formTO = formTO;
	}
}
