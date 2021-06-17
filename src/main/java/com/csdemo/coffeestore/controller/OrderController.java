package com.csdemo.coffeestore.controller;

import com.csdemo.coffeestore.contract.OrderConfirmation;
import com.csdemo.coffeestore.dto.CartRequest;
import com.csdemo.coffeestore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( value="/order" )
@Slf4j
public class OrderController{
	@Autowired
	private OrderService orderService;
	
	@PostMapping( value="/confirm/{orderId}" )
	public ResponseEntity confirmOrder ( @PathVariable( "orderId" ) int orderId ) {
		OrderConfirmation cartItems=orderService.confirmOrder ( orderId );
		return new ResponseEntity ( cartItems , HttpStatus.ACCEPTED );
	}
	
	
	@PostMapping( value="/addtocart" )
	public ResponseEntity cart ( @RequestBody Map < String, List < CartRequest > > orderRequest ) {
		OrderConfirmation response=orderService.createCart ( orderRequest.get ( "Items" ) );
		return new ResponseEntity ( response , HttpStatus.ACCEPTED );
		
		
	}
	
	
}
