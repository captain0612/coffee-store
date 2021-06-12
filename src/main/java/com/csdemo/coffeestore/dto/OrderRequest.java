package com.csdemo.coffeestore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderRequest {
    private int quantity;
    private String name;

}
