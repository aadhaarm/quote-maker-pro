package com.lounge3.quotemakerpro.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.NSHomePresenter;

public class NSHomeView extends Composite implements NSHomePresenter.Display {

	private final Label lbMainPageText;

	public NSHomeView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);

		lbMainPageText = new Label(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis tristique erat a leo hendrerit feugiat. " +
				"Ut sit amet ante libero, id pretium dui. In nec velit sed nibh tincidunt euismod. Donec quis nulla nec nibh interdum malesuada. " +
				"In scelerisque arcu nec quam posuere vel fringilla nibh varius. Nunc nibh ipsum, malesuada vel vestibulum sit amet, commodo sit amet enim. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
				"Phasellus volutpat facilisis eros id bibendum. Pellentesque malesuada sagittis bibendum. " +
				"Mauris et dui turpis. Pellentesque at dui at ligula consectetur dictum a eget turpis.");

		// Create the menu
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(lbMainPageText);
		contentTableDecorator.add(hPanel);
	}

	public Widget asWidget() {
		return this;
	}

}