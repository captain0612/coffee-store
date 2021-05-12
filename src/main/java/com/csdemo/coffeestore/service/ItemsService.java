package com.csdemo.coffeestore.service;

import com.csdemo.coffeestore.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsService {

    @Autowired
    private ItemsRepository itemsRepo;

    public List<String> getItemsList() {
        return itemsRepo.findAllItems();
    }
}
