package com.csdemo.coffeestore.controller;

import com.csdemo.coffeestore.contract.ItemsResponse;
import com.csdemo.coffeestore.contract.OrderResponse;
import com.csdemo.coffeestore.dto.OrderRequest;
import com.csdemo.coffeestore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService ;

    @PostMapping
    public ResponseEntity orderRequest(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponses;
        orderResponses = orderService.placeOrder(orderRequest);
        if (orderResponses!=null) {
            return new ResponseEntity<>(orderResponses, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Item not available in the Menu", HttpStatus.ACCEPTED);
    }
}
