package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface LoginEventHandler extends EventHandler {
  void onLoginLinkClicked(LoginEvent event);
}
