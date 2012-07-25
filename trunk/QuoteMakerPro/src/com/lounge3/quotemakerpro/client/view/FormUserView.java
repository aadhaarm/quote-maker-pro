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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.FormUserPresenter;
import com.lounge3.quotemakerpro.client.processor.FormProcessor;
import com.lounge3.quotemakerpro.client.widgets.ListBoxProductQuantity;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.TO.FormCategoryTO;
import com.lounge3.quotemakerpro.shared.TO.FormProductTO;
import com.lounge3.quotemakerpro.shared.TO.FormTO;
import com.lounge3.quotemakerpro.shared.TO.ProductSaveTO;

public class FormUserView extends Composite implements FormUserPresenter.Display {

	private final FlexTable formTable;
	private VerticalPanel contentPanel;
	private Label lblTotalValue;
	private final Button btnSaveForm;

	FormProcessor formPro;

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

		contentTableDecorator.add(contentPanel);
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public void setFormData(FormTO formTO) {
		if(formTO != null) {

			// TODO: Form Title
			formTable.setWidget(0, 0, new Label(formTO.getTitle()));
			formTable.setWidget(0, 1, new Label(formTO.getDescription()));

			List<FormCategoryTO> categories = formTO.getCategories();
			if(categories != null) {
				int rowIndex = 3;

				//Iterate categories
				for (Iterator<FormCategoryTO> iterator = categories.iterator(); iterator.hasNext();) {
					FormCategoryTO formCategoryTO = (FormCategoryTO) iterator.next();
					if(formCategoryTO != null) {
						//TODO: Cat Titles and desc
						formTable.setWidget(rowIndex, 1, new Label(formCategoryTO.getTitle()));
						formTable.setWidget(rowIndex, 2, new Label(formCategoryTO.getDescription()));
						rowIndex++;


						List<FormProductTO> products = formCategoryTO.getProducts();
						if(products != null) {
							// Iterate Products
							for (Iterator<FormProductTO> iterator2 = products.iterator(); iterator2.hasNext();) {
								final FormProductTO formProductTO = (FormProductTO) iterator2.next();

								//List Box Quantity								
								final ListBoxProductQuantity lbQuantity = getQuantityListBox(formProductTO);;

								//Text Box Quantity
								final TextBox txtQuantity = getQuantityTextBox(formProductTO);;

								CheckBox cbProduct = null;

								if(formProductTO != null) {


									if(Constants.QUANTITY_TYPE_QUANTITY_BASED.equalsIgnoreCase(formProductTO.getElementQuantityType())) {

										if(Constants.QUANTITY_ALGO_ANY.equalsIgnoreCase(formProductTO.getQuantityAlgo())) {
											
												formTable.setWidget(rowIndex, 4, txtQuantity);
										
										} else {
											
											if(Constants.QUANTITY_ALGO_MULTIPLE.equalsIgnoreCase(formProductTO.getQuantityAlgo())) {
												for(long i=formProductTO.getMinQuantity(); i<=formProductTO.getMaxQuantity(); i++) {
													if(i % Integer.parseInt(formProductTO.getMultiOrMan()) == 0) {
														lbQuantity.addItem(formProductTO.getMultiOrMan(), formProductTO.getMultiOrMan());
													}
												}
											} else if(Constants.QUANTITY_ALGO_MANUAL.equalsIgnoreCase(formProductTO.getQuantityAlgo())) {
												if(formProductTO.getMultiOrMan() != null) {
													String [] qtyVals = formProductTO.getMultiOrMan().split(",");
													for(int i=0; i<qtyVals.length; i++) {
														lbQuantity.addItem(qtyVals[i], qtyVals[i]);
													}
												}
											}
											formTable.setWidget(rowIndex, 4, lbQuantity);
										
										}
									}

									// Product Selection Check Box
									cbProduct = getProductSelectionCheckBox(
											formProductTO, lbQuantity,
											txtQuantity);

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
				lblTotalValue.setText(Double.toString(formPro.updateTotal()));
			}
		}
	}

	private CheckBox getProductSelectionCheckBox(
			final FormProductTO formProductTO,
			final ListBoxProductQuantity lbQuantity, final TextBox txtQuantity) {
		CheckBox cbProduct;
		cbProduct = new CheckBox();
		processCheckBox(cbProduct, formProductTO);
		cbProduct.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				CheckBox cbProduct = (CheckBox) event.getSource();
				if(cbProduct.getValue()) {

					if(Constants.QUANTITY_ALGO_ANY.equalsIgnoreCase(formProductTO.getQuantityAlgo())) {
						formPro.addProduct(formProductTO, Long.parseLong(txtQuantity.getText()));
						txtQuantity.setEnabled(true);
					} else {
						formPro.addProduct(formProductTO, Long.parseLong(lbQuantity.getValue(lbQuantity.getSelectedIndex())));
						lbQuantity.setEnabled(true);
					}

					lblTotalValue.setText(Double.toString(formPro.updateTotal()));

				} else {
					formPro.removeProduct(formProductTO);

					if(Constants.QUANTITY_ALGO_ANY.equalsIgnoreCase(formProductTO.getQuantityAlgo())) {
						txtQuantity.setEnabled(false);
					} else {
						lbQuantity.setEnabled(false);
					}

					lblTotalValue.setText(Double.toString(formPro.updateTotal()));

				}
			}
		});
		return cbProduct;
	}

	private TextBox getQuantityTextBox(final FormProductTO formProductTO) {
		final TextBox txtQuantity = new TextBox();
		txtQuantity.setEnabled(false);
		txtQuantity.setText("1");
		txtQuantity.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				TextBox txtQuantity = (TextBox)event.getSource();
				formPro.updateProductQuantity(formProductTO, Long.parseLong(txtQuantity.getText()));
				lblTotalValue.setText(Double.toString(formPro.updateTotal()));
			}
		});
		return txtQuantity;
	}

	private ListBoxProductQuantity getQuantityListBox(
			final FormProductTO formProductTO) {
		final ListBoxProductQuantity lbQuantity = new ListBoxProductQuantity();
		lbQuantity.setEnabled(false);
		lbQuantity.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				ListBoxProductQuantity lbQuantity = (ListBoxProductQuantity) event.getSource();
				if(lbQuantity.isEnabled()) {
					formPro.updateProductQuantity(formProductTO, Long.parseLong(lbQuantity.getValue(lbQuantity.getSelectedIndex())));
					lblTotalValue.setText(Double.toString(formPro.updateTotal()));
				}
			}
		});
		return lbQuantity;
	}

	private void processCheckBox(CheckBox cbProduct, FormProductTO formProductTO) {
		if(formPro.isSelected(formProductTO)) {
			cbProduct.setValue(true);
			lblTotalValue.setText(Double.toString(formPro.updateTotal()));
		}
	}

	@Override
	public void setSelectedProductsObject(
			Map<String, ProductSaveTO> selectedProducts) {
		this.formPro = new FormProcessor(selectedProducts);
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return this.btnSaveForm;
	}
}