package com.smartbank.demo.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.smartbank.demo.dto.City;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class H2RepositoryTest {

  @Mock
  H2Repository h2Repository;
  @Mock
  Connection connection;

  @BeforeAll
  void setup() {
    h2Repository = mock(H2Repository.class);
    connection = mock(Connection.class);
  }

  @Test
  void migrate() throws SQLException {
    h2Repository.migrate(connection);
    List<City> cities = h2Repository.readAll(connection);
    Assertions.assertEquals(50, cities.size());
  }

  @Test
  void readAll() {
  }
}