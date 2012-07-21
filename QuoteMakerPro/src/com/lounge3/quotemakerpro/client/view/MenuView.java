package com.lounge3.quotemakerpro.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.lounge3.quotemakerpro.client.presenter.MenuPresenter;

public class MenuView extends Composite implements MenuPresenter.Display {

	private Button btnCategoryList;
	private Anchor ancAddCategory;

	private Button btnProductList;
	private Anchor ancAddProduct;
	
	private Button btnFormList;
	private Anchor ancAddForm;
	
	VerticalPanel vpanelCategory;
	VerticalPanel vpanelProduct;
	VerticalPanel vpanelForm;
	
	public MenuView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		
		btnCategoryList = new Button("Category List");
		ancAddCategory = new Anchor("Add Category");
		vpanelCategory = new VerticalPanel();
		vpanelCategory.add(btnCategoryList);
		vpanelCategory.add(ancAddCategory);

		vpanelProduct = new VerticalPanel();
		btnProductList = new Button("Products");
		ancAddProduct = new Anchor("Add Product");
		vpanelProduct.add(btnProductList);
		vpanelProduct.add(ancAddProduct);

		vpanelForm = new VerticalPanel();
		btnFormList = new Button("Forms");
		ancAddForm = new Anchor("Add Form");
		vpanelForm.add(btnFormList);
		vpanelForm.add(ancAddForm);
		
		StackPanel panel = new StackPanel();
	    panel.add(vpanelCategory.asWidget(), "Categories");
	    panel.add(vpanelProduct, "Products");
	    panel.add(vpanelForm, "Forms");

		contentTableDecorator.add(panel);
	}

	@Override
	public HasClickHandlers getCategoryListButton() {
		return btnCategoryList;
	}

	@Override
	public HasClickHandlers getProductListButton() {
		return btnProductList;
	}

	@Override
	public HasClickHandlers getFormListButton() {
		return btnFormList;
	}

	@Override
	public HasClickHandlers getAncAddCategoryLink() {
		return ancAddCategory;
	}

	@Override
	public HasClickHandlers getAncAddProductLink() {
		return ancAddProduct;
	}

	@Override
	public HasClickHandlers getAncAddFormLink() {
		return ancAddForm;
	}
}