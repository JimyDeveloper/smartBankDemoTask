package com.smartbank.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

  public static List<City> getAll() throws IOException {
    List<City> collect;
    try (Stream<String> linesAsString = Files.lines(
        Path.of(FileReader.class.getClassLoader().getResource("myFile0.csv").getPath()))) {
      collect = linesAsString.map(line -> {
        String[] strings = line.split(",");
        return new City(strings[0], strings[1]);
      }).collect(Collectors.toList());
    }
    return collect;
  }

  public List<City> getAl() {
    String line = "";
    String splitBy = ",";
    try
    {
//parsing a CSV file into BufferedReader class constructor
      BufferedReader br = new BufferedReader(new FileReader("CSVDemo.csv"));
      while ((line = br.readLine()) != null)   //returns a Boolean value
      {
        String[] employee = line.split(splitBy);    // use comma as separator
        System.out.println("Employee [First Name=" + employee[0] + ", Last Name=" + employee[1] + ", Designation=" + employee[2] + ", Contact=" + employee[3] + ", Salary= " + employee[4] + ", City= " + employee[5] +"]");
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    System.out.println(getAll());
  }
}
