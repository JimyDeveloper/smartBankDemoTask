package com.smartbank.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.smartbank.demo.model.City;
import com.smartbank.demo.repos.CityRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class DBUnitTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  CityRepository cityRepository;

  @Test
  @DisplayName("Test db contains no data")
  void testReposIsEmpty() {
    List<City> cityList = cityRepository.findAll();

    assertThat(cityList).isEmpty();
  }

  @Test
  @DisplayName("Test whether repository saves an entity")
  void testRepositorySavings() {
    City city = cityRepository.save(new City(251186, "Iquitos"));

    assertThat(city).hasFieldOrPropertyWithValue("code", 251186L);
    assertThat(city).hasFieldOrPropertyWithValue("name", "Iquitos");
  }

  @Test
  @DisplayName("Test whether repository saves entities")
  void testRepositorySavingsMultiRows() {
    City city1 = new City(208177, "Rabat");
    entityManager.persist(city1);

    City city2 = new City(157900, "Kingston");
    entityManager.persist(city2);

    City city3 = new City(2906, "Perth");
    entityManager.persist(city3);

    List<City> cityList = cityRepository.findAll();

    assertThat(cityList).hasSize(3)
        .contains(city1, city2, city3);
  }

  @Test
  @DisplayName("Test whether saved data equal to actual entity")
  void testRepositoryFindByIdMethod() {
    City city1 = new City(208177, "Rabat");
    entityManager.persist(city1);

    City city2 = new City(157900, "Kingston");
    entityManager.persist(city2);

    City foundCity = cityRepository.findById(city2.getId()).get();

    assertThat(foundCity).isEqualTo(city2);
  }

  @Test
  @DisplayName("Test whether data updated properly")
  void testRepositoryUpdate() {
    City city1 = new City(208177, "Rabat");
    entityManager.persist(city1);

    City city2 = new City(157900, "Kingston");
    entityManager.persist(city2);

    City updatedCity = cityRepository.findById(city1.getId()).get();
    updatedCity.setCode(city2.getCode());
    updatedCity.setName(city2.getName());
    cityRepository.save(updatedCity);

    City checkCity = cityRepository.findById(city1.getId()).get();

    assertThat(checkCity.getCode()).isEqualTo(updatedCity.getCode());
    assertThat(checkCity.getName()).isEqualTo(updatedCity.getName());
  }

  @Test
  @DisplayName("Test whether data deleted")
  void testRepositoryDelete() {
    City city1 = new City(208177, "Rabat");
    entityManager.persist(city1);

    City city2 = new City(157900, "Kingston");
    entityManager.persist(city2);

    City city3 = new City(2906, "Perth");
    entityManager.persist(city3);

    cityRepository.deleteById(city2.getId());

    List<City> cityList = cityRepository.findAll();

    assertThat(cityList).hasSize(2).contains(city1, city3);
  }

  @Test
  @DisplayName("Test Repository delete all")
  void testRepositoryDeleteAll() {
    entityManager.persist(new City(208177, "Rabat"));
    entityManager.persist(new City(157900, "Kingston"));

    cityRepository.deleteAll();

    assertThat(cityRepository.findAll()).isEmpty();
  }
}
