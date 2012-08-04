package com.lounge3.quotemakerpro.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.EditProductPresenter;
import com.lounge3.quotemakerpro.client.widgets.L3TxtBox;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;

public class EditProductView extends Composite implements EditProductPresenter.Display {

	private final L3TxtBox txtProductName;
	private final L3TxtBox txtProductDesc;
	private final L3TxtBox txtProductTitle;
	private final L3TxtBox txtProductPrice;

	private final RadioButton rbSelectionBased;
	private final RadioButton rbQuantityBased;

	private final L3TxtBox txtMinQuantity;
	private final L3TxtBox txtMaxQuantity;
	private final ListBox lbQuantityAlgo;
	private final L3TxtBox txtQuantityManOrMulti;
	private final L3TxtBox txtQuantityUnit;

	private final Label lblQuantityRule;
	private final Label lblMinQuantity;
	private final Label lblMaxQuantity;

	private final FlexTable detailsTable;
	private final Button saveButton;
	private final Button cancelButton;

	private final String ERROR_MESSAGE = "Please enter a valid input.";

	private ListBox lbCategory;


	public EditProductView() {
		DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		initWidget(contentDetailsDecorator);

		lbCategory = new ListBox(true);
		lbCategory.setVisibleItemCount(3);		

		VerticalPanel contentDetailsPanel = new VerticalPanel();

		detailsTable = new FlexTable();
		txtProductName = new L3TxtBox();
		txtProductDesc = new L3TxtBox();
		txtProductPrice = new L3TxtBox();
		txtProductTitle = new L3TxtBox();
		rbQuantityBased = new RadioButton("quantityType", "Quantity Based");
		rbSelectionBased = new RadioButton("quantityType", "Selection Based");
		rbSelectionBased.setValue(true);
		txtMinQuantity = new L3TxtBox();
		txtMaxQuantity = new L3TxtBox();

		lbQuantityAlgo = new ListBox();
		lbQuantityAlgo.addItem("Any quantity", Constants.QUANTITY_ALGO_ANY);
		lbQuantityAlgo.addItem("Multiple", Constants.QUANTITY_ALGO_MULTIPLE);
		lbQuantityAlgo.addItem("Manual", Constants.QUANTITY_ALGO_MANUAL);
		lbQuantityAlgo.setVisibleItemCount(1);

		txtQuantityManOrMulti = new L3TxtBox();
		txtQuantityManOrMulti.setVisible(false);

		txtQuantityUnit = new L3TxtBox();

		detailsTable.setWidget(0, 0, new Label("Name"));
		detailsTable.setWidget(0, 1, txtProductName);
		detailsTable.setWidget(1, 0, new Label("Description"));
		detailsTable.setWidget(1, 1, txtProductDesc);
		detailsTable.setWidget(2, 0, new Label("Title"));
		detailsTable.setWidget(2, 1, txtProductTitle);
		detailsTable.setWidget(3, 0, new Label("Price"));
		detailsTable.setWidget(3, 1, txtProductPrice);
		detailsTable.setWidget(4, 0, new Label("Category"));
		detailsTable.setWidget(4, 1, lbCategory);
		detailsTable.setWidget(5, 0, new Label("Quantity Type"));
		detailsTable.setWidget(5, 1, rbSelectionBased);
		detailsTable.setWidget(6, 1, rbQuantityBased);


		txtProductName.setFocus(true);
		contentDetailsPanel.add(detailsTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);

		contentDetailsPanel.add(menuPanel);

		contentDetailsDecorator.add(contentDetailsPanel);

		//Initialize widgets;		
		lblMinQuantity = new Label("Minimum quantity");
		lblMaxQuantity = new Label("Maximum quantity");
		lblQuantityRule = new Label("Quantity rule");
		initializeWidgets();

		bind();
		setupValidations();
	}

	public Widget asWidget() {
		return this;
	}

	private void bind() {

		rbQuantityBased.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				lblQuantityRule.setVisible(true);
				lbQuantityAlgo.setVisible(true);
				if(Constants.QUANTITY_ALGO_ANY.equalsIgnoreCase(lbQuantityAlgo.getValue(lbQuantityAlgo.getSelectedIndex()))) {
					lblMinQuantity.setVisible(true);
					txtMinQuantity.setVisible(true);
					lblMaxQuantity.setVisible(true);
					txtMaxQuantity.setVisible(true);
				} else if(Constants.QUANTITY_ALGO_MULTIPLE.equalsIgnoreCase(lbQuantityAlgo.getValue(lbQuantityAlgo.getSelectedIndex()))) {
					lblMinQuantity.setVisible(true);
					txtMinQuantity.setVisible(true);
					lblMaxQuantity.setVisible(true);
					txtMaxQuantity.setVisible(true);
					txtQuantityManOrMulti.setVisible(true);
				} else if(Constants.QUANTITY_ALGO_MANUAL.equalsIgnoreCase(lbQuantityAlgo.getValue(lbQuantityAlgo.getSelectedIndex()))) {
					txtQuantityManOrMulti.setVisible(true);
				}
			}
		});

		rbSelectionBased.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(((RadioButton)event.getSource()).getValue()) {
					lblQuantityRule.setVisible(false);
					lbQuantityAlgo.setVisible(false);
					lblMinQuantity.setVisible(false);
					txtMinQuantity.setVisible(false);
					lblMaxQuantity.setVisible(false);
					txtMaxQuantity.setVisible(false);
					txtQuantityManOrMulti.setVisible(false);
				}
			}
		});

		lbQuantityAlgo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if(Constants.QUANTITY_ALGO_MANUAL.equalsIgnoreCase(((ListBox)event.getSource()).getValue(((ListBox)event.getSource()).getSelectedIndex()))) {
					lblMinQuantity.setVisible(false);
					txtMinQuantity.setVisible(false);
					lblMaxQuantity.setVisible(false);
					txtMaxQuantity.setVisible(false);
				} else {
					lblMinQuantity.setVisible(true);
					txtMinQuantity.setVisible(true);
					lblMaxQuantity.setVisible(true);
					txtMaxQuantity.setVisible(true);
				}

				if(!Constants.QUANTITY_ALGO_ANY.equalsIgnoreCase(((ListBox)event.getSource()).getValue(((ListBox)event.getSource()).getSelectedIndex()))) {
					txtQuantityManOrMulti.setVisible(true);
				} else {
					txtQuantityManOrMulti.setVisible(false);
				}
			}
		});
	}
	
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	@Override
	public HasValue<String> getDescription() {
		return txtProductDesc;
	}

	public ListBox getLbQuantityAlgo() {
		return lbQuantityAlgo;
	}

	@Override
	public HasValue<String> getName() {
		return txtProductName;
	}

	@Override
	public HasValue<String> getPrice() {
		return txtProductPrice;
	}

	@Override
	public HasValue<String> getProductTitle() {
		return txtProductTitle;
	}

	public RadioButton getRbQuantityBased() {
		return rbQuantityBased;
	}

	public RadioButton getRbSelectionBased() {
		return rbSelectionBased;
	}

	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	@Override
	public List<String> getSelectedCategories() {
		List<String> selectedCategories = new ArrayList<String>();
		for(int i=0; i < lbCategory.getItemCount(); i++) {
			if(lbCategory.isItemSelected(i)) {
				selectedCategories.add(lbCategory.getValue(i));
			}
		}
		return selectedCategories;
	}

	public L3TxtBox getTxtMaxQuantity() {
		return txtMaxQuantity;
	}

	public L3TxtBox getTxtMinQuantity() {
		return txtMinQuantity;
	}

	@Override
	public HasValue<String> getTxtQualtityMultiOrManual() {
		return txtQuantityManOrMulti;
	}

	public L3TxtBox getTxtQuantityUnit() {
		return txtQuantityUnit;
	}

	private void initializeWidgets() {
		txtMinQuantity.setMaxLength(3);
		txtMaxQuantity.setMaxLength(3);
		txtMinQuantity.setText("1");
		txtMaxQuantity.setText("1");
		detailsTable.setWidget(7, 0, lblQuantityRule);
		detailsTable.setWidget(7, 1, lbQuantityAlgo);
		detailsTable.setWidget(8, 1, txtQuantityManOrMulti);

		detailsTable.setWidget(9, 0, lblMinQuantity);
		detailsTable.setWidget(9, 1, txtMinQuantity);

		detailsTable.setWidget(10, 0, lblMaxQuantity);
		detailsTable.setWidget(10, 1, txtMaxQuantity);

		detailsTable.setWidget(11, 0, new Label("Quantity unit"));
		detailsTable.setWidget(11, 1, txtQuantityUnit);

		lblQuantityRule.setVisible(false);
		lbQuantityAlgo.setVisible(false);
		lblMinQuantity.setVisible(false);
		txtMinQuantity.setVisible(false);
		lblMaxQuantity.setVisible(false);
		txtMaxQuantity.setVisible(false);
		txtQuantityManOrMulti.setVisible(false);
	}

	@Override
	public void setData(List<ElementTO> data) {
		for (ElementTO elementTO : data) {
			lbCategory.addItem(elementTO.getTitle(), elementTO.getId().toString());
		}
	}

	private void setupValidations() {
		txtQuantityManOrMulti.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				validateQuantityManOrMulti();
			}

		});

		txtProductName.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				validateProductName();
			}

		});

		txtProductTitle.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				validateProductTitle();
			}

		});

		txtProductPrice.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				validatePrice();
			}

		});

		txtQuantityUnit.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				validateUnit();
			}

		});

		txtMinQuantity.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
					validateMinQuantity();
			}

		});

		txtMaxQuantity.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
					validateMaxQuantity();
			}

		});
	}

	public boolean validateForm() {
		boolean priceOk = validatePrice();
		boolean nameOk = validateProductName();
		boolean minQtyOk = validateMinQuantity();
		boolean maxQtyOk = validateMaxQuantity();
		boolean titleOk = validateProductTitle();
		boolean unitOk = validateUnit();
		boolean manOrMultiOk = validateQuantityManOrMulti();
		
		if( priceOk && nameOk && minQtyOk && maxQtyOk && titleOk && unitOk && manOrMultiOk) {
			return true;
		}

		return false;
	}

	private boolean validateMaxQuantity() {
		if(txtMaxQuantity.isVisible()) {
			return txtMaxQuantity.validate(Constants.REGEX_POSITIVE_NUMBER, ERROR_MESSAGE);
		} else {
			return true;
		}
	}

	private boolean validateMinQuantity() {
		if(txtMinQuantity.isVisible()) {
			return txtMinQuantity.validate(Constants.REGEX_POSITIVE_NUMBER, ERROR_MESSAGE);
		} else {
			return true;
		}
	}

	private boolean validatePrice() {
		return txtProductPrice.validate(Constants.REGEX_CURRENCY, ERROR_MESSAGE);
	}

	private boolean validateProductName() {
		return txtProductName.isEmpty(ERROR_MESSAGE);
	}

	private boolean validateProductTitle() {
		return txtProductTitle.isEmpty(ERROR_MESSAGE);
	}

	private boolean validateQuantityManOrMulti() {
		if(txtQuantityManOrMulti.isVisible()) {
			if(Constants.QUANTITY_ALGO_MANUAL.equalsIgnoreCase(lbQuantityAlgo.getValue(lbQuantityAlgo.getSelectedIndex()))) {
				return txtQuantityManOrMulti.validate(Constants.REGEX_CSV_NUMBERS, ERROR_MESSAGE);
			} else  {
				return txtQuantityManOrMulti.validate(Constants.REGEX_POSITIVE_NUMBER, ERROR_MESSAGE);
			}
		}
		return true;
	}

	private boolean validateUnit() {
		return txtQuantityUnit.isEmpty(ERROR_MESSAGE);
	}
}