package com.csdemo.coffeestore.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoldItemResponse {

  private String name;
  private long total;

  public SoldItemResponse(String name, long total) {
    this.name = name;
    this.total = total;
  }
}
