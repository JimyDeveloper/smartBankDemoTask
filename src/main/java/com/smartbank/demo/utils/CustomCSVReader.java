package com.smartbank.demo.utils;

import com.smartbank.demo.comparator.CityComparatorByCode;
import com.smartbank.demo.comparator.CityComparatorByName;
import com.smartbank.demo.model.City;
import com.smartbank.demo.model.CustomSortedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.ResourceUtils;

public class CustomCSVReader {
  private static  final String resourceLocation = "classpath:static/myFile0.csv";

  public static CustomSortedList<City> readAllSorted() {
    CustomSortedList<City> collect = new CustomSortedList<>();
    long start = 0;
    try (BufferedReader br = new BufferedReader(
        new FileReader(ResourceUtils.getFile(resourceLocation)))) {
      String header = br.readLine();
      String line;
      start = System.currentTimeMillis();
      while ((line = br.readLine()) != null) {
        String[] cityAttr = line.split(",");
        collect.add(new City(Long.parseLong(cityAttr[0]), cityAttr[1]));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    long finish = System.currentTimeMillis();
    System.out.println("Time to insert by sorting: " + (finish - start) + "ms");
    return collect;
  }

  public static List<City> readAll() {
    List<City> collect = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(
        new FileReader(ResourceUtils.getFile(resourceLocation)))) {
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
