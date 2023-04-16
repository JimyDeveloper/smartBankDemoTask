package com.smartbank.demo;


import com.smartbank.demo.model.City;
import com.smartbank.demo.model.CustomSortedList;
import com.smartbank.demo.utils.CustomCSVReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomCSVReaderTest {

  @Test
  @DisplayName("Test function of custom alphabetical order by name")
  void testCitiesInAlphabeticalOrderAccepted() {
    int index = 555;
    List<City> expectedList = CustomCSVReader.readAll().stream()
        .sorted(Comparator.comparing(City::getName))
        .collect(Collectors.toList());

    List<City> actualList = CustomCSVReader.getCitiesInAlphabeticalOrder();
    Assertions.assertEquals(expectedList.get(index).getName(), actualList.get(index).getName(),
        "Sort order at index " + index);
  }

  @Test
  @DisplayName("Test function of custom numerical order by code")
  void testCitiesInNumericalOrderAccepted() {
    int index = 869;
    List<City> expectedList = CustomCSVReader.readAll().stream()
        .sorted(Comparator.comparingLong(City::getCode))
        .collect(Collectors.toList());

    List<City> actualList = CustomCSVReader.getCitiesInNumericalOrder();
    Assertions.assertEquals(expectedList.get(index).getName(), actualList.get(index).getName(),
        "Sort order at index " + index);
  }

  @Test
  @DisplayName("Test whether custom data structure stores elements in sorted order")
  void testWhetherCustomDataStructureIsSorted() {
    int index = 999;
    List<City> expectedList = CustomCSVReader.readAll().stream()
        .sorted(Comparator.comparing(City::getName))
        .collect(Collectors.toList());


    CustomSortedList<City> actualList = CustomCSVReader.readAllSorted();

    Assertions.assertTrue(expectedList.get(index).getName().equals(actualList.getAll().get(index).getName()),
        "Sort order at index " + index);
  }
}