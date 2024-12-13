package com.io.order.model.internal;

import java.math.BigDecimal;

public interface ProductInternal {
    String getProductId();
    String getName();
    BigDecimal getPrice();
    int getQuantity();
}
