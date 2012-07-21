package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ProductEventHandler extends EventHandler {
  void onAddProductClicked(ProductEvent event);
}
