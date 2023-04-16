package com.smartbank.demo.repos;

import com.smartbank.demo.model.City;
import java.util.List;

public interface CityRepository extends BaseRepository<City, Long>  {

  List<City> findByName(String name);
  City findByCode(Long code);
}
