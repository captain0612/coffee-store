package com.csdemo.coffeestore.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemsResponse {

    private String name;

    private int quantity;

    private int price;
}
