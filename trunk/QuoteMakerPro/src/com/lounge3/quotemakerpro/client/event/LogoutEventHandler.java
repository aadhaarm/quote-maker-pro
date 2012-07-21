package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface LogoutEventHandler extends EventHandler {
	void onLogoutLinkClicked(LogoutEvent event);
}
