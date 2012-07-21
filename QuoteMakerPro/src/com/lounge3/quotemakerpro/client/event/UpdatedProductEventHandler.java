package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface UpdatedProductEventHandler extends EventHandler {
  void onProductUpdate(UpdatedProductEvent event);
}
