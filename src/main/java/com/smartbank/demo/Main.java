package com.smartbank.demo;

import com.smartbank.demo.dao.H2Repository;
import com.smartbank.demo.dto.City;
import com.smartbank.demo.utility.CustomCSVReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public Main() {
  }

  public static void main(String[] args) {
    List<City> sortedAlphabetically = CustomCSVReader.readAll().stream()
        .sorted(Comparator.comparing(City::getName))
        .collect(Collectors.toList());
    System.out.println(sortedAlphabetically.size());

    System.out.println("------");

    List<City> citiesSortedByCode = CustomCSVReader.getCitiesInNumericalOrder();
    System.out.println(citiesSortedByCode.size());

    System.out.println("------");

    List<City> citiesSortedByName = CustomCSVReader.getCitiesInAlphabeticalOrder();
    System.out.println(citiesSortedByName.size());

    System.out.println("------DB------");

    H2Repository h2Repository = new H2Repository();
    Connection connect = null;
    try {
      connect = h2Repository.connect();
      h2Repository.migrate(connect);

      System.out.println("-------");

      List<City> cities = h2Repository.readAll(connect);
      System.out.println(cities.size());

    } catch (SQLException e) {
      System.err.println("Error on execution!");
    } finally {
      h2Repository.disconnect(connect);
    }
  }
}
