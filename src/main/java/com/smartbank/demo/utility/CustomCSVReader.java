package com.smartbank.demo.utility;

import com.smartbank.demo.comparator.CityComparatorByCode;
import com.smartbank.demo.comparator.CityComparatorByName;
import com.smartbank.demo.dto.City;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomCSVReader {


  public static List<City> readAll() {
    List<City> collect = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(
        new FileReader(CustomCSVReader.class.getClassLoader().getResource("myFile0.csv").getPath()))) {
      String header = br.readLine();
      String line;
      while ((line = br.readLine()) != null) {
        String[] cityAttr = line.split(",");
        collect.add(new City(Long.parseLong(cityAttr[0]), cityAttr[1]));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return collect;
  }

  public static List<City> getCitiesInAlphabeticalOrder() {
    List<City> cities = readAll();
    long start = System.currentTimeMillis();
    cities.sort(new CityComparatorByName());
    long finish = System.currentTimeMillis();
    System.out.println("Sorted in " + (finish - start) + "ms");
    return cities;
  }

  public static List<City> getCitiesInNumericalOrder() {
    List<City> cities = readAll();
    long start = System.currentTimeMillis();
    cities.sort(new CityComparatorByCode());
    long finish = System.currentTimeMillis();
    System.out.println("Sorted in " + (finish - start) + "ms");
    return cities;
  }
}
