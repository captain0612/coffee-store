package com.csdemo.coffeestore.repository;

import com.csdemo.coffeestore.contract.ItemsResponse;
import com.csdemo.coffeestore.contract.OrderResponse;
import com.csdemo.coffeestore.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface ItemsRepository extends JpaRepository<Items, Integer> {

    @Query("select new com.csdemo.coffeestore.contract.ItemsResponse(items.name, items.quantity, items.price) from Items items")
    public List<ItemsResponse> findAllItems();
    @Query("FROM Items item WHERE name = ?1")
    public Items findByname(String itemName);

}
