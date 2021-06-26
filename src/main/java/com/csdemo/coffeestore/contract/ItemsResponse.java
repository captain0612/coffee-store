package com.csdemo.coffeestore.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ItemsResponse {

  private String name;

  private int quantity;

  private int price;

  public static void main(String args[]) {
    List<List<LinkedHashMap<String, String>>> lobs = new ArrayList<>();
    List<LinkedHashMap<String, String>> value = new ArrayList<>();

    LinkedHashMap<String, String> b1 = new LinkedHashMap<>();
    LinkedHashMap<String, String> b2 = new LinkedHashMap<>();
    LinkedHashMap<String, String> b3 = new LinkedHashMap<>();

    b1.put("name", "CCB");
    b1.put("id", "123");
    b1.put("rsid", "12");

    b2.put("name", "CAT>CLM>RAR");
    b2.put("id", "1234");
    b2.put("rsid", "12");

    b3.put("name", "CHR");
    b3.put("id", "5433");
    b3.put("rsid", "43");

    value.add(b1);
    value.add(b2);
    value.add(b3);

    lobs.add(value);

    List<String> lob1 = new ArrayList<>();
    List<String> lob2 = new ArrayList<>();
    List<String> lob3 = new ArrayList<>();

    value.forEach(
        lob -> {
          List<String> lobNames = Arrays.asList(lob.get("name").split(">"));
          if (lobNames.size() > 0) {
            lob1.add(lobNames.get(0));
          }
          if (lobNames.size() > 1) {
            lob2.add(lobNames.get(1));
          }
          if (lobNames.size() > 2) {
            lob3.add(lobNames.get(2));
          }
        });
    System.out.println("lob 1 :");
    lob1.forEach(
        buss -> {
          System.out.print(buss);
          System.out.println();
        });
    System.out.println("lob 2 :");
    lob2.forEach(
        buss -> {
          System.out.print(buss);
          System.out.println();
        });
    System.out.println("lob 3 :");
    lob3.forEach(
        buss -> {
          System.out.print(buss);
          System.out.println();
        });
  }
}
