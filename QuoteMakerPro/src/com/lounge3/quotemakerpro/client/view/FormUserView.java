package com.lounge3.quotemakerpro.client.view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.FormUserPresenter;
import com.lounge3.quotemakerpro.client.util.ClientUtils;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.TO.FormCategoryTO;
import com.lounge3.quotemakerpro.shared.TO.FormProductTO;
import com.lounge3.quotemakerpro.shared.TO.FormTO;
import com.lounge3.quotemakerpro.shared.TO.ProductSaveTO;

public class FormUserView extends Composite implements FormUserPresenter.Display {

	private final FlexTable formTable;
	private VerticalPanel contentPanel;
	private long formFilledTotal;
	private Label lblTotalValue;
	private final Button btnSaveForm;

	Map<String, ProductSaveTO> selectedProducts;

	public FormUserView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);

		HorizontalPanel menuPanel = new HorizontalPanel();
		btnSaveForm = new Button("Save");
		menuPanel.add(btnSaveForm);
		formTable = new FlexTable();

		lblTotalValue = new Label();
		contentPanel = new VerticalPanel();
		contentPanel.add(formTable);
		contentPanel.add(menuPanel);
		contentPanel.add(lblTotalValue);

		formFilledTotal = 0;

		contentTableDecorator.add(contentPanel);
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public void setFormData(FormTO formTO) {
		if(formTO != null) {
			formTable.setWidget(0, 0, new Label(formTO.getTitle()));
			formTable.setWidget(0, 1, new Label(formTO.getDescription()));

			List<FormCategoryTO> categories = formTO.getCategories();
			if(categories != null) {
				int rowIndex = 3;
				for (Iterator<FormCategoryTO> iterator = categories.iterator(); iterator.hasNext();) {
					FormCategoryTO formCategoryTO = (FormCategoryTO) iterator.next();
					if(formCategoryTO != null) {
						formTable.setWidget(rowIndex, 1, new Label(formCategoryTO.getTitle()));
						formTable.setWidget(rowIndex, 2, new Label(formCategoryTO.getDescription()));
						rowIndex++;
						List<FormProductTO> products = formCategoryTO.getProducts();
						if(products != null) {
							for (Iterator<FormProductTO> iterator2 = products.iterator(); iterator2.hasNext();) {
								final FormProductTO formProductTO = (FormProductTO) iterator2.next();

								final TextBox txtQuantity = new TextBox();
								final ListBox lbQuantity = new ListBox();
								lbQuantity.setEnabled(false);
								lbQuantity.addChangeHandler(new ChangeHandler() {
									
									@Override
									public void onChange(ChangeEvent event) {
										if(((ListBox)event.getSource()).isEnabled()) {
											formProductTO.getPrice()+ = formProductTO.getPrice() * (((ListBox)event.getSource()).get);
										}
									}
								});
								
								
								CheckBox cbProduct = null;


								if(formProductTO != null) {

									cbProduct = new CheckBox();

									if(Constants.QUANTITY_TYPE_QUANTITY_BASED.equalsIgnoreCase(formProductTO.getElementQuantityType())) {

										if(Constants.QUANTITY_ALGO_ANY.equalsIgnoreCase(formProductTO.getQuantityAlgo())) {

											txtQuantity.setEnabled(false);
											txtQuantity.setText("1");

											txtQuantity.addValueChangeHandler(new ValueChangeHandler<String>() {
												@Override
												public void onValueChange(ValueChangeEvent<String> event) {
													formFilledTotal+= formProductTO.getPrice() * (Integer.parseInt(((TextBox) event.getSource()).getValue()) - 1);
													selectedProducts.put(formProductTO.getProductId().toString(), ClientUtils.getProductSaveTO(formProductTO));
													lblTotalValue.setText(Long.toString(formFilledTotal));
												}
											});

											formTable.setWidget(rowIndex, 4, txtQuantity);

										} else if(Constants.QUANTITY_ALGO_MULTIPLE.equalsIgnoreCase(formProductTO.getQuantityAlgo())) {
											for(long i=formProductTO.getMinQuantity(); i<=formProductTO.getMaxQuantity(); i++) {
												if(i%Integer.parseInt(formProductTO.getMultiOrMan()) == 0) {
													lbQuantity.addItem(formProductTO.getMultiOrMan(), formProductTO.getMultiOrMan());
												}
											}
											formTable.setWidget(rowIndex, 4, lbQuantity);
										
										} else if(Constants.QUANTITY_ALGO_MANUAL.equalsIgnoreCase(formProductTO.getQuantityAlgo())) {
											if(formProductTO.getMultiOrMan() != null) {
												String [] qtyVals = formProductTO.getMultiOrMan().split(",");
												for(int i=0; i<qtyVals.length; i++) {
													lbQuantity.addItem(qtyVals[i], qtyVals[i]);
												}
											}
											formTable.setWidget(rowIndex, 4, lbQuantity);
										}

									}
									configureCheckBox(cbProduct, formProductTO);

									cbProduct.addClickHandler(new ClickHandler() {
										@Override
										public void onClick(ClickEvent event) {
											if(((CheckBox) event.getSource()).getValue()) {
												selectedProducts.put(formProductTO.getProductId().toString(), ClientUtils.getProductSaveTO(formProductTO));

												if(Constants.QUANTITY_ALGO_ANY.equalsIgnoreCase(formProductTO.getQuantityAlgo())) {
													formFilledTotal+= formProductTO.getPrice() * formProductTO.getMinQuantity();
													txtQuantity.setEnabled(true);
												} else {
													formFilledTotal+= formProductTO.getPrice() * formProductTO.getMinQuantity();
													lbQuantity.setEnabled(true);
												}

												lblTotalValue.setText(Long.toString(formFilledTotal));

											} else {
												if(Constants.QUANTITY_ALGO_ANY.equalsIgnoreCase(formProductTO.getQuantityAlgo())) {
													if(txtQuantity.isEnabled()) {
														formFilledTotal-= formProductTO.getPrice() * Integer.parseInt(txtQuantity.getValue());
													} else {
														formFilledTotal-= formProductTO.getPrice();
													}

													txtQuantity.setEnabled(false);
													txtQuantity.setText("1");
												} else {
													if(lbQuantity.isEnabled()) {
														formFilledTotal-= formProductTO.getPrice() * Integer.parseInt(lbQuantity.getValue(lbQuantity.getSelectedIndex()));
													} else {
														formFilledTotal-= formProductTO.getPrice();
													}
													
													lbQuantity.setEnabled(false);
												}

												selectedProducts.remove(formProductTO.getProductId());
												lblTotalValue.setText(Long.toString(formFilledTotal));

											}
										}
									});

									formTable.setWidget(rowIndex, 0, cbProduct);
									formTable.setWidget(rowIndex, 1, new Label(formProductTO.getTitle()));
									formTable.setWidget(rowIndex, 2, new Label(formProductTO.getDescription()));
									formTable.setWidget(rowIndex, 3, new Label(formProductTO.getPrice().toString()));
									formTable.setWidget(rowIndex, 5, new Label(formProductTO.getQuantityUnit()));

									rowIndex++;
								}
							}
						}
					}
				}
				lblTotalValue.setText(Long.toString(formFilledTotal));
			}
		}
	}

	private void configureCheckBox(CheckBox cbProduct, FormProductTO formProductTO) {
		if(selectedProducts != null) {
			if(selectedProducts.containsKey(formProductTO.getProductId().toString())) {
				cbProduct.setValue(true);
				formFilledTotal+= formProductTO.getPrice();
			}
		}
	}

	@Override
	public void setSelectedProductsObject(
			Map<String, ProductSaveTO> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return this.btnSaveForm;
	}
}