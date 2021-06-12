package com.csdemo.coffeestore.contract;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
@Getter
@Setter

public class OrderResponse {

    private String name;

    private String availability;

    private int price;

    }

