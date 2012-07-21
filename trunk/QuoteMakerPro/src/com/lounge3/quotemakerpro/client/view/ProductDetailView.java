package com.lounge3.quotemakerpro.client.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.ProductDetailPresenter;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;

public class ProductDetailView extends Composite implements ProductDetailPresenter.Display {

	private Button btnEditProduct;
	private Button btnCancel;
	private final FlexTable productTable;
	private VerticalPanel contentPanel;
	private ListBox lbCategory = new ListBox(true);


	public ProductDetailView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);

		productTable = new FlexTable();
		productTable.setWidget(0, 0, new Label("Product Name"));
		productTable.setWidget(1, 0, new Label("Description"));
		productTable.setWidget(2, 0, new Label("Price"));
		productTable.setWidget(3, 0, new Label("Title"));
		productTable.setWidget(4, 0, new Label("Category"));

		btnCancel = new Button("Cancel");
		btnEditProduct = new Button("Edit");
		contentPanel = new VerticalPanel();
		
		contentPanel.add(productTable);
		
		contentPanel.add(btnEditProduct);
		contentPanel.add(btnCancel);

		contentTableDecorator.add(contentPanel);
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getCategoryList() {
		return productTable;
	}

	@Override
	public String getClickedProduct(ClickEvent event) {
		HTMLTable.Cell cell = productTable.getCellForEvent(event);
		String selectedProduct = null;
		if (cell != null) {
			int selectedRow = -1;
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
				return selectedProduct = productTable.getText(selectedRow, 0);
			}
		}

		return selectedProduct;
	}

	@Override
	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();

		for (int i = 1; i <= productTable.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox)productTable.getWidget(i, 0);
			if (checkBox.getValue()) {
				selectedRows.add(i);
			}
		}

		return selectedRows;
	}

	@Override
	public HasClickHandlers getProductTable() {
		return productTable;
	}

	@Override
	public void setAssociatedCategoryData(List<ElementTO> data) {
		if(data != null) {
			for (Iterator<ElementTO> iterator = data.iterator(); iterator.hasNext();) {
				ElementTO categoryTO = (ElementTO) iterator.next();
				lbCategory.addItem(categoryTO.getName() + "( Title: " + categoryTO.getTitle() + " )");
			}
		}
		productTable.setWidget(4, 1, lbCategory);
	}

	@Override
	public void setData(ElementTO data) {
		productTable.setText(0, 1, data.getName());
		productTable.setText(1, 1, data.getDescription());
		productTable.setText(2, 1, data.getPrice().toString());
		productTable.setText(3, 1, data.getTitle());
	}

	@Override
	public HasClickHandlers getEditButton() {
		return btnEditProduct;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return btnCancel;
	}
}