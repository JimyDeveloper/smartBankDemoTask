package com.smartbank.demo.dao;

import com.smartbank.demo.dto.City;
import com.smartbank.demo.utility.CustomCSVReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2Repository {

  public Connection connect() throws SQLException {
    String jdbcURL = "jdbc:h2:mem:test";

    Connection connection = DriverManager.getConnection(jdbcURL);

    System.out.println("Connected to H2 in-memory database.");

    return connection;
  }

  public void disconnect(Connection connection) {
    try {
      connection.close();
    } catch (SQLException e) {
      System.err.println("Error on close connection!");
    }
  }

  private void init(Connection connection) throws SQLException {
    String sql = "Create table city (ID int primary key, name varchar(50))";
    Statement statement = connection.createStatement();
    statement.execute(sql);
    System.out.println("Executed Query.");
  }

  public void execute(String sql, Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    int rows = statement.executeUpdate(sql);
    if (rows > 0) {
      System.out.println("Inserted a new row.");
    }
  }

  public void migrate(Connection connection) throws SQLException {
    init(connection);
    List<City> collect = CustomCSVReader.readAll();
    StringBuilder sqlb = new StringBuilder();
    for (City city : collect.subList(0, 50)) {
      sqlb.append("Insert into city (id, name) values (")
          .append(city.getCode()).append(", '").append(city.getName())
          .append("');");
    }
    execute(sqlb.toString(), connection);
  }

  public List<City> readAll(Connection connection) throws SQLException {
    List<City> cities = new ArrayList<>();

    Statement statement = connection.createStatement();
    String sql = "select * from city";
    ResultSet rs = statement.executeQuery(sql);
    while (rs.next()) {
      int cityCode = rs.getInt("id");
      String cityName = rs.getString("name");
      cities.add(new City(cityCode, cityName));
    }
    return cities;
  }
}
