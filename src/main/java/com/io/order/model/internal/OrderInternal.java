package com.io.order.model.internal;

import java.util.Date;

public interface OrderInternal {
    String getOrderId();
    String getClientId();
    Date getOrderDate();
}
