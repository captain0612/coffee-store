package com.csdemo.coffeestore.repository;

import com.csdemo.coffeestore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {}
