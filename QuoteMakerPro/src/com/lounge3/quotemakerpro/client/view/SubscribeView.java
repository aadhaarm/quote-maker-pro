	package com.lounge3.quotemakerpro.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.SubscribePresenter;

public class SubscribeView extends Composite implements SubscribePresenter.Display {

	private final FlexTable categoryTable;
	private final VerticalPanel contentPanel;
	private final TextBox txtScreenName;
	private final Button btnCheckAvailability;
	private final Button btnSubscribe;
	
	public SubscribeView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);

		btnCheckAvailability = new Button("Check Availability");
		txtScreenName = new TextBox();
		
		categoryTable = new FlexTable();
		categoryTable.setWidget(0, 0, new Label("Screen Name"));
		categoryTable.setWidget(0, 1, txtScreenName);
		categoryTable.setWidget(1, 1, btnCheckAvailability);
		
		btnSubscribe = new Button("Subscribe");
		contentPanel = new VerticalPanel();
		contentPanel.add(categoryTable);
		contentPanel.add(btnSubscribe);
		
		contentTableDecorator.add(contentPanel);
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getCheckAvailabilityButton() {
		return btnCheckAvailability;
	}

	@Override
	public HasClickHandlers getSubscribeButton() {
		return btnSubscribe;
	}

	@Override
	public String getScreenNameText() {
		return txtScreenName.getText();
	}
}