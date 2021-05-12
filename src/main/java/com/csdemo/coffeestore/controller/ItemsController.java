package com.csdemo.coffeestore.controller;

import com.csdemo.coffeestore.service.ItemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        List<String> itemsMenu = new ArrayList<>();
        try {
            itemsMenu = itemsService.getItemsList();
        } catch (Exception ex) {
            log.error("exception while fetching items menu : {}", ex.getMessage());
        }

        return new ResponseEntity<List<String>>(itemsMenu, HttpStatus.ACCEPTED);
    }
}
