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
import com.lounge3.quotemakerpro.client.presenter.CategoryPresenter;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;

public class CategoryView extends Composite implements CategoryPresenter.Display {

	private Button addCategoryButton;
	private final FlexTable categoryTable;
	private VerticalPanel contentPanel;

	public CategoryView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);

		categoryTable = new FlexTable();
		categoryTable.setWidget(0, 0, new Label("Category-Name"));
		categoryTable.setWidget(0, 1, new Label("Title"));
		categoryTable.setWidget(0, 2, new Label("Description"));

		addCategoryButton = new Button("Add Category");
		contentPanel = new VerticalPanel();
		contentPanel.add(categoryTable);
		//		contentPanel.add(addCategoryButton);
		contentTableDecorator.add(contentPanel);
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getCategoryList() {
		return categoryTable;
	}

	@Override
	public void setData(List<ElementTO> data) {
		categoryTable.removeAllRows();

		categoryTable.setWidget(0, 0, new Label("Category-Name"));
		categoryTable.setWidget(0, 1, new Label("Title"));
		categoryTable.setWidget(0, 2, new Label("Description"));

		if(data != null) {
			for (int i = 0; i < data.size(); ++i) {
				if(data.get(i) != null) {
					categoryTable.setText(i+1, 0, data.get(i).getName());
					categoryTable.setText(i+1, 1, data.get(i).getTitle());
					categoryTable.setText(i+1, 2, data.get(i).getDescription());
					
				}
			}
		}
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = categoryTable.getCellForEvent(event);

		if (cell != null) {
			// Suppress clicks if the user is actually selecting the check box
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
			}
		}

		return selectedRow;
	}

	@Override
	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();

		for (int i = 1; i <= categoryTable.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox)categoryTable.getWidget(i, 0);
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
}