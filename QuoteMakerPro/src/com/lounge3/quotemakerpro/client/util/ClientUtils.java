package com.lounge3.quotemakerpro.client.util;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.lounge3.quotemakerpro.client.proxy.FormServiceAsync;
import com.lounge3.quotemakerpro.shared.Constants;
import com.lounge3.quotemakerpro.shared.LoginInfo;
import com.lounge3.quotemakerpro.shared.TO.FormProductTO;
import com.lounge3.quotemakerpro.shared.TO.ProductSaveTO;
import com.lounge3.quotemakerpro.shared.TO.UserTO;

public class ClientUtils {

	public static ProductSaveTO getProductSaveTO(FormProductTO formProductTO, Long quantity) {
		ProductSaveTO selectedProduct = null;
		if(formProductTO != null) {
			selectedProduct = new ProductSaveTO();
			selectedProduct.setProductId(formProductTO.getProductId());
			selectedProduct.setQuotedPrice(formProductTO.getPrice());
			selectedProduct.setQuantity(quantity);
		}
		return selectedProduct;
	}

	public static void checkSubscribed(final LoginInfo loginInfo,
			FormServiceAsync rpcService) {
		
		if ("".equals(History.getToken()) && loginInfo.isLoggedIn()) {
			addScreenNameToURL(loginInfo.getScreenName(), "login");
		} else if ("".equals(History.getToken()) && !loginInfo.isLoggedIn()) {
			String screenName = Window.Location.getParameter("sn");
			if(screenName != null) {
				loginInfo.setScreenName(screenName);
				History.newItem(Constants.HISTORYITEM_LIST_FORM);
			} else {
				History.newItem("logout");
			}
		} else {
			History.fireCurrentHistoryState();
		}
	}

	public static void subscribeUser(FormServiceAsync rpcService,
			HandlerManager eventBus, LoginInfo loginInfo, final String screenNameText) {

		// Prepare User TO
		UserTO userTO = new UserTO();
		userTO.setUserEmail(loginInfo.getEmailAddress());
		userTO.setSuscribed(true);
		userTO.setScreenName(screenNameText);

		rpcService.suscribeUser(userTO, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				addScreenNameToURL(screenNameText);
			}


			@Override
			public void onFailure(Throwable caught) {
				Window.alert(Constants.SERVER_ERROR);
			}
		});
	}

	private static void addScreenNameToURL(String screenName, String hashVal) {
		String url = Window.Location.getHref();
		if(!url.contains(screenName)) {

			if(url.contains("?")) {
				if(!url.contains("#")) {
				url = url.replace(url, url +"&sn="+ screenName +"#" + hashVal);
				} else {
					url = url.replace("#", "&sn="+ screenName +"#");
				}
			} else {
				if(!url.contains("#")) {
					url = url.replace(url, url + "?sn="+ screenName +"#" + hashVal);
				} else {
					url = url.replace("#", "?sn="+ screenName +"#");
				}
			}

			Window.Location.assign(url);
		}
	}

	private static void addScreenNameToURL(String screenName) {
		String url = Window.Location.getHref();
		if(!url.contains(screenName)) {

			if(url.contains("?")) {
				url = url.replace("#", "&sn="+ screenName +"#");
			} else {
				url = url.replace("#", "?sn="+ screenName +"#");
			}

			Window.Location.assign(url);
		}
	}
}