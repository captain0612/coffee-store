package com.csdemo.coffeestore.controller;

import com.csdemo.coffeestore.contract.ItemsResponse;
import com.csdemo.coffeestore.dto.ItemsRequest;
import com.csdemo.coffeestore.service.ItemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/items")
@Slf4j
public class ItemsController {

  @Autowired private ItemsService itemsService;

  @PostMapping
  public ResponseEntity postItem(@RequestBody Map<String, List<ItemsRequest>> itemRequest) {
    itemsService.addItems(itemRequest.get("items"));
    return getItemsList();
  }

  @GetMapping
  public ResponseEntity getItemsList() {
    List<ItemsResponse> itemsMenu = new ArrayList<>();
    try {
      itemsService.getItemsList();
    } catch (Exception ex) {
      log.error("exception while fetching items menu : {}", ex.getMessage());
    }
    return new ResponseEntity<List<ItemsResponse>>(itemsMenu, HttpStatus.ACCEPTED);
  }

  @DeleteMapping(value = "/delete/{itemName}")
  public ResponseEntity deletePost(@PathVariable String itemName) {
    itemsService.deleteItemByName(itemName);
    return new ResponseEntity(HttpStatus.ACCEPTED);
  }
}
