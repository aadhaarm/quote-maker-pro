package com.lounge3.quotemakerpro.client.view;

import java.util.ArrayList;
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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.ProductPresenter;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;

public class ProductView extends Composite implements ProductPresenter.Display {

	private Button addCategoryButton;
	private final FlexTable productTable;
	private VerticalPanel contentPanel;

	public ProductView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);

		productTable = new FlexTable();
		productTable.setWidget(0, 0, new Label("Product Name"));
		productTable.setWidget(0, 1, new Label("Title"));
		
		addCategoryButton = new Button("Add Product");
		contentPanel = new VerticalPanel();
		contentPanel.add(productTable);

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
	public void setData(List<ElementTO> data) {
		productTable.removeAllRows();
		productTable.setWidget(0, 0, new Label("Product Name"));
		productTable.setWidget(0, 1, new Label("Title"));

		for (int i = 0; i < data.size(); ++i) {
			productTable.setText(i+1, 0, data.get(i).getName());
			productTable.setText(i+1, 1, data.get(i).getTitle());
		}
	}

	@Override
	public String getClickedProduct(ClickEvent event) {
		HTMLTable.Cell cell = productTable.getCellForEvent(event);
		String selectedProduct = null;
		if (cell != null) {
			int selectedRow = -1;
			if (cell.getCellIndex() > -1) {
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
	public HasClickHandlers getAddCategoryButton() {
		return addCategoryButton;
	}

	@Override
	public HasClickHandlers getProductTable() {
		return productTable;
	}
}