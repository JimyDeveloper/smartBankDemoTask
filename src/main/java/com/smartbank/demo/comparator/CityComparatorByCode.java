package com.smartbank.demo.comparator;

import com.smartbank.demo.model.City;
import java.util.Comparator;

public class CityComparatorByCode implements Comparator<City> {
  @Override
  public int compare(City city1, City city2) {
    return Long.compare(city1.getCode(), city2.getCode());
  }
}
