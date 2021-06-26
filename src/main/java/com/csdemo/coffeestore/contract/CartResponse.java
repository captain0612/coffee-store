package com.csdemo.coffeestore.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {

  private String name;
  private String availableQuantity;
  private int pricePerItem;
  private int price;
}
