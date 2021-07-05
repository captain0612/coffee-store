package com.csdemo.coffeestore.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class OrderConfirmation {

  private int orderTotal;
  private List<CartResponse> ResponseList = new ArrayList<>();
  private int Order_id;
}
