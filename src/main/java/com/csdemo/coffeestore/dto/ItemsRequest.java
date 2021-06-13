package com.csdemo.coffeestore.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class ItemsRequest {


    private String name;

    private int quantity;

    private int price;
}
