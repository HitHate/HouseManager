package com.vo;

import com.entity.Checkout;
import com.entity.Userlist;
import lombok.Data;

@Data
public class CheckoutBean extends Checkout {
    private Userlist userlist;
}
