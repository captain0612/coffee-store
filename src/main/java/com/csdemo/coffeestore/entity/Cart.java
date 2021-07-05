package com.csdemo.coffeestore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Data
@Entity
@Table(name = "cart")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int cart_id;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "order_id")
  private Order order;

  @Column private String name;
  @Column private int quantity;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDateTime;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updateDateTime;

  public Cart() {}

  public Cart(Order order) {
    this.order = order;
  }
}
