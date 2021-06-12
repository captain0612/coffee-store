package com.csdemo.coffeestore.service;

import com.csdemo.coffeestore.contract.ItemsResponse;
import com.csdemo.coffeestore.dto.ItemsRequest;
import com.csdemo.coffeestore.entity.Items;
import com.csdemo.coffeestore.repository.ItemsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsService {

    @Autowired
    private ItemsRepository itemsRepo;


    public List<ItemsResponse> getItemsList() {
        return itemsRepo.findAllItems();
    }

    public  Boolean addItems (ItemsRequest itemRequest) {
        Items item = new Items();
        BeanUtils.copyProperties(itemRequest,item);
        Items repoItem = itemsRepo.findByname(item.getName());
        if(repoItem== null) {
            itemsRepo.save(item);
            return true;

        }
        int exitingQuantity = repoItem.getQuantity();
        int requestQuantity = itemRequest.getQuantity();
        repoItem.setQuantity(exitingQuantity+requestQuantity);
        int requestPrice = itemRequest.getPrice();
        repoItem.setPrice(requestPrice);
        itemsRepo.save(repoItem);
        return false;
    }
    public  Boolean deleteItemByName (String itemName)
    {
       Items repoItem = itemsRepo.findByname(itemName);
        if(repoItem!= null)
        {
            itemsRepo.delete(repoItem);
            return true;
        }

        return false;
    }
}
