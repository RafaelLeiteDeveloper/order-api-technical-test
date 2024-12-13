package com.io.order.model.internal;

import java.math.BigDecimal;
import java.util.Date;

public interface OrderInternal {
    String getOrderId();
    String getClientId();
    Date getOrderDate();
    BigDecimal getTotalCost();
}
