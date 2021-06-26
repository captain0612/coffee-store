package com.csdemo.coffeestore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "items")
public class Items {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "price")
  private int price;
}
