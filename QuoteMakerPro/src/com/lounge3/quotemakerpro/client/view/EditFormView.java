package com.lounge3.quotemakerpro.client.view;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.controller.ElementDragController;
import com.lounge3.quotemakerpro.client.controller.ElementDropController;
import com.lounge3.quotemakerpro.client.presenter.EditFormPresenter;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;

public class EditFormView extends Composite implements EditFormPresenter.Display {

	private final TextBox txtFormName;
	private final TextBox txtFormTitle;
	private final RichTextArea txtFormDescription;
	private final FlexTable formTable;
	private final FlexTable categoryTable;
	private HorizontalPanel categoryPanel;
	private final Button saveButton;
	private final Button cancelButton;
	
	private ElementDragController categoryDragController;
	private ElementDropController formDropController;
		
	public EditFormView() {
		DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		initWidget(contentDetailsDecorator);
		
		FlexTable contentDetailsPanel = new FlexTable();

		categoryTable = new FlexTable();
		formTable = new FlexTable();
		
		categoryPanel = new HorizontalPanel();
		
		txtFormName = new TextBox();
		txtFormTitle = new TextBox();
		txtFormDescription = new RichTextArea();
		formTable.setWidget(0, 0, new Label("Name"));
		formTable.setWidget(0, 1, txtFormName);
		formTable.setWidget(1, 0, new Label("Title"));
		formTable.setWidget(1, 1, txtFormTitle);
		formTable.setWidget(2, 0, new Label("Description"));
		formTable.setWidget(2, 1, txtFormDescription);
		
		txtFormName.setFocus(true);

		contentDetailsPanel.setWidget(0, 0, formTable);
		contentDetailsPanel.setWidget(0, 1, categoryTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);

		contentDetailsPanel.setWidget(4, 2, menuPanel);
		
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

	public boolean setupDND(ElementDragController categoryDragController, ElementDropController elementDropController) {
		this.categoryDragController = categoryDragController;
		this.formDropController = elementDropController;
		categoryDragController.registerDropController(formDropController);
		return true;
	}
	
	@Override
	public void setCategories(List<ElementTO> categoryList) {
	
		int row = 0;
		for (Iterator<ElementTO> iterator = categoryList.iterator(); iterator.hasNext();) {
			ElementTO elementTO = (ElementTO) iterator.next();
			categoryPanel = new HorizontalPanel();
			Label lblCategoryName = new Label(elementTO.getName());
			categoryPanel.add(lblCategoryName);
			categoryTable.setWidget(row, 0, categoryPanel);
			categoryDragController.makeDraggable(lblCategoryName);
			row++;
		}
	}

	@Override
	public Widget getFormTable() {
		return formTable;
	}

	@Override
	public String getFormName() {
		return txtFormName.getText();
	}

	@Override
	public String getFormTitle() {
		return txtFormTitle.getText();
	}

	@Override
	public String getFormDesc() {
		return txtFormDescription.getText();
	}
}