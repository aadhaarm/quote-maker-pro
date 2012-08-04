package com.lounge3.quotemakerpro.shared;


public final class Constants {

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	public static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";
	
	public static final String HISTORYITEM_LIST_CATEGORY = "ListCategory";
	
	public static final String ELEMENT_TYPE_PRODUCT = "product";

	public static final String HISTORYITEM_EDIT_FORM = "EditForm";

	public static final String HISTORYITEM_LIST_FORM = "ListForm";

	public static final String FORM_STATE_SAVE = "save";

	public static final String HISTORYITEM_SHOW_SAVED_FORM = "openSaveform";

	public static final String HISTORYITEM_SUBSCRIBE = "subscribe";

	public static final String QUANTITY_TYPE_QUANTITY_BASED = "quantityBased";

	public static final String QUANTITY_TYPE_SELECTION_BASED = "selectionBased";

	public static final String QUANTITY_ALGO_ANY = "any";

	public static final String QUANTITY_ALGO_MULTIPLE = "multiple";

	public static final String QUANTITY_ALGO_MANUAL = "manual";

	public static final String DIV_RIGHT_STATUS = "rightHandStatusBarDiv";

	public static final String DIV_LEFT_MENU = "leftHandMenuDiv";

	public static final String DIV_MAIN_CONTENT = "bodyDiv";

	public static final String HISTORYITEM_LOGOUT = "logout";

	public static final String HISTORYITEM_LOGIN = "login";

	public static final String HISTORYITEM_ADD_CATEGORY = "AddCategory";

	public static final String HISTORYITEM_ADD_PRODUCT = "AddProduct";

	public static final String HISTORYITEM_LIST_PRODUCTS = "ListProducts";

	public static final String HISTORYITEM_PRODUCT_DETAILS = "ProductDetail";

	public static final String HISTORYITEM_EDIT_PRODUCT = "EditProduct";

	public static final String STYLE_TEXTBOX_VALIDATION_ERROR_STYLE = "error-text-box";

	public static final String REGEX_CURRENCY = "^\\$?([0-9]{1,3},([0-9]{3},)*[0-9]{3}|[0-9]+)(.[0-9][0-9])?$";

	public static final String REGEX_CSV_NUMBERS = "^[0-9]+(,[0-9]+)*$";

	public static final String REGEX_POSITIVE_NUMBER = "^\\d+$";
}