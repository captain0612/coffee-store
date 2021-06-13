package com.csdemo.coffeestore.controller;

import com.csdemo.coffeestore.contract.OrderConfirmation;
import com.csdemo.coffeestore.contract.OrderResponse;
import com.csdemo.coffeestore.dto.OrderRequest;
import com.csdemo.coffeestore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService ;

    @PostMapping
    public ResponseEntity orderRequest(@RequestBody Map<String, List<OrderRequest>> orderRequest) {
        List<OrderResponse> orderResponses;
        orderResponses = orderService.placeOrder(orderRequest.get("Items"));
        if (orderResponses != null) {
            return new ResponseEntity<>(orderResponses, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Items not available in the Menu", HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/confirm/{responce}")
    public ResponseEntity confirmOrder(@PathVariable("responce") String responce) {

        if (responce.equals("YES")) {
            OrderConfirmation obj = orderService.confirmOrder();

            return new ResponseEntity<OrderConfirmation>(obj, HttpStatus.ACCEPTED);
        }
        orderService.clearOrder();
        return new ResponseEntity<>("OrderCleared", HttpStatus.ACCEPTED);
    }
}
