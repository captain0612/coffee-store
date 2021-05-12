package com.csdemo.coffeestore.repository;

import com.csdemo.coffeestore.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Items, Integer> {

    @Query("select items.name from Items items")
    public List<String> findAllItems();
}
