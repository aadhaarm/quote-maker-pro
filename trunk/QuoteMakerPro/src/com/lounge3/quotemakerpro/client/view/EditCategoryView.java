package com.lounge3.quotemakerpro.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.EditCategoryPresenter;

public class EditCategoryView extends Composite implements EditCategoryPresenter.Display {

	private final TextBox txtCategoryName;
	private final TextBox txtCategoryDesc;
	private final TextBox txtCategoryTitle;
	private final FlexTable detailsTable;
	private final Button saveButton;
	private final Button cancelButton;

	public EditCategoryView() {
		DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		initWidget(contentDetailsDecorator);

		VerticalPanel contentDetailsPanel = new VerticalPanel();

		detailsTable = new FlexTable();
		txtCategoryName = new TextBox();
		txtCategoryDesc = new TextBox();
		txtCategoryTitle = new TextBox();
		
		detailsTable.setWidget(0, 0, new Label("Name"));
		detailsTable.setWidget(0, 1, txtCategoryName);
		detailsTable.setWidget(1, 0, new Label("Title"));
		detailsTable.setWidget(1, 1, txtCategoryTitle);
		detailsTable.setWidget(2, 0, new Label("Description"));
		detailsTable.setWidget(2, 1, txtCategoryDesc);
		txtCategoryName.setFocus(true);
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
		return txtCategoryName;
	}

	@Override
	public HasValue<String> getDescription() {
		return txtCategoryDesc;
	}

	@Override
	public HasValue<String> getCategoryTitle() {
		return txtCategoryTitle;
	}
}