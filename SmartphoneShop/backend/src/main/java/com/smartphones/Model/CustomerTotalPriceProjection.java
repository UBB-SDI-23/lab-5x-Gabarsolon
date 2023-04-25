package com.smartphones.Model;

import java.math.BigDecimal;

public interface CustomerTotalPriceProjection {
    Long getId();
    String getFirstName();
    String getLastName();
    BigDecimal getTotalPrice();
}
