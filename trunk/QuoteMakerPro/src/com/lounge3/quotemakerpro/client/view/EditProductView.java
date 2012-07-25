package com.lounge3.quotemakerpro.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.EditProductPresenter;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;

public class EditProductView extends Composite implements EditProductPresenter.Display {

	private final TextBox txtProductName;
	private final TextBox txtProductDesc;
	private final TextBox txtProductTitle;
	private final TextBox txtProductPrice;
	
	private final RadioButton rbSelectionBased;
	private final RadioButton rbQuantityBased;
	
	private final TextBox txtMinQuantity;
	private final TextBox txtMaxQuantity;
	private final ListBox lbQuantityAlgo;
	private final TextBox txtQuantityManOrMulti;
	private final TextBox txtQuantityUnit;
	
	private final FlexTable detailsTable;
	private final Button saveButton;
	private final Button cancelButton;

	private ListBox lbCategory;


	public EditProductView() {
		DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		initWidget(contentDetailsDecorator);

		lbCategory = new ListBox(true);
		lbCategory.setVisibleItemCount(3);		
		
		VerticalPanel contentDetailsPanel = new VerticalPanel();

		detailsTable = new FlexTable();
		txtProductName = new TextBox();
		txtProductDesc = new TextBox();
		txtProductPrice = new TextBox();
		txtProductTitle = new TextBox();
		rbQuantityBased = new RadioButton("quantityType", "Quantity Based");
		rbSelectionBased = new RadioButton("quantityType", "Selection Based");
		txtMinQuantity = new TextBox();
		txtMinQuantity.setMaxLength(3);
		txtMaxQuantity = new TextBox();
		txtMaxQuantity.setMaxLength(3);
		
		lbQuantityAlgo = new ListBox();
		lbQuantityAlgo.addItem("Any quantity", Constants.QUANTITY_ALGO_ANY);
		lbQuantityAlgo.addItem("Multiple", Constants.QUANTITY_ALGO_MULTIPLE);
		lbQuantityAlgo.addItem("Manual", Constants.QUANTITY_ALGO_MANUAL);
		lbQuantityAlgo.setVisibleItemCount(1);
		
		lbQuantityAlgo.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				if(!Constants.QUANTITY_ALGO_ANY.equalsIgnoreCase(((ListBox)event.getSource()).getValue(((ListBox)event.getSource()).getSelectedIndex()))) {
					txtQuantityManOrMulti.setVisible(true);
				} else {
					txtQuantityManOrMulti.setVisible(false);
				}
			}
		});

		txtQuantityManOrMulti = new TextBox();
		txtQuantityManOrMulti.setVisible(false);
		
		txtQuantityUnit = new TextBox();
		
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
		detailsTable.setWidget(5, 1, rbQuantityBased);
		detailsTable.setWidget(6, 1, rbSelectionBased);
		detailsTable.setWidget(7, 0, new Label("Minimum quantity"));
		detailsTable.setWidget(7, 1, txtMinQuantity);
		detailsTable.setWidget(8, 0, new Label("Maximum quantity"));
		detailsTable.setWidget(8, 1, txtMaxQuantity);
		detailsTable.setWidget(9, 0, new Label("Quantity rule"));
		detailsTable.setWidget(9, 1, lbQuantityAlgo);
		
		detailsTable.setWidget(10, 1, txtQuantityManOrMulti);
		
		detailsTable.setWidget(11, 0, new Label("Quantity unit"));
		detailsTable.setWidget(11, 1, txtQuantityUnit);

		txtProductName.setFocus(true);
		contentDetailsPanel.add(detailsTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);

		contentDetailsPanel.add(menuPanel);

		contentDetailsDecorator.add(contentDetailsPanel);
	}

	public Widget asWidget() {
		return this;
	}

	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	@Override
	public HasValue<String> getName() {
		return txtProductName;
	}

	@Override
	public HasValue<String> getDescription() {
		return txtProductDesc;
	}

	@Override
	public HasValue<String> getProductTitle() {
		return txtProductTitle;
	}

	@Override
	public HasValue<String> getPrice() {
		return txtProductPrice;
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

	@Override
	public void setData(List<ElementTO> data) {
		for (ElementTO elementTO : data) {
			lbCategory.addItem(elementTO.getTitle(), elementTO.getId().toString());
		}
	}

	public TextBox getTxtMinQuantity() {
		return txtMinQuantity;
	}

	public TextBox getTxtMaxQuantity() {
		return txtMaxQuantity;
	}

	public ListBox getLbQuantityAlgo() {
		return lbQuantityAlgo;
	}

	public TextBox getTxtQuantityUnit() {
		return txtQuantityUnit;
	}

	public RadioButton getRbSelectionBased() {
		return rbSelectionBased;
	}

	public RadioButton getRbQuantityBased() {
		return rbQuantityBased;
	}

	@Override
	public HasValue<String> getTxtQualtityMultiOrManual() {
		return txtQuantityManOrMulti;
	}
}