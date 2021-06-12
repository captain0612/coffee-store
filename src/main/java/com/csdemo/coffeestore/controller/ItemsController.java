package com.csdemo.coffeestore.controller;

import com.csdemo.coffeestore.contract.ItemsResponse;
import com.csdemo.coffeestore.dto.ItemsRequest;
import com.csdemo.coffeestore.entity.Items;
import com.csdemo.coffeestore.repository.ItemsRepository;
import com.csdemo.coffeestore.service.ItemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/items")
@Slf4j
public class ItemsController {

    @Autowired
    private ItemsService itemsService;



    @GetMapping
    public ResponseEntity getItemsList() {

        List<ItemsResponse> itemsMenu = new ArrayList<>();
        try {
            itemsMenu = itemsService.getItemsList();
        } catch (Exception ex) {
            log.error("exception while fetching items menu : {}", ex.getMessage());
        }

        return new ResponseEntity<List<ItemsResponse>>(itemsMenu, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity postItem(@RequestBody ItemsRequest itemRequest) {
        if (itemsService.addItems(itemRequest))
        return new ResponseEntity<>("item added in the menu",HttpStatus.ACCEPTED);

        else
            return new ResponseEntity<>("Updated Quantity and Price of "+itemRequest.getName()+" ",HttpStatus.CONFLICT);
    }

    @DeleteMapping(value = "/delete/{itemName}")
    public ResponseEntity<Long> deletePost(@PathVariable String itemName) {
        Boolean isRemoved = itemsService.deleteItemByName(itemName);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("Deleted", HttpStatus.OK);

    }

}
