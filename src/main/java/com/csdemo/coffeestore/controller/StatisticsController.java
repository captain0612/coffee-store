package com.csdemo.coffeestore.controller;

import com.csdemo.coffeestore.contract.SoldItemResponse;
import com.csdemo.coffeestore.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/soldCount")
@Slf4j
public class StatisticsController {
  @Autowired private StatisticsService soldItemService;

  @GetMapping(value = "/{noOfDays}")
  public ResponseEntity getItemsList(@PathVariable("noOfDays") int noOfDays) {
    List<SoldItemResponse> lst = new ArrayList<>();
    try {
      lst = soldItemService.fetchitemCount(noOfDays);
    } catch (Exception ex) {
      log.error("exception while fetching the data : {}", ex.getMessage());
    }
    return new ResponseEntity<List<SoldItemResponse>>(lst, HttpStatus.ACCEPTED);
  }
}
