package com.smartbank.demo.service.impl;

import com.smartbank.demo.model.City;
import com.smartbank.demo.repos.BaseRepository;
import com.smartbank.demo.repos.CityRepository;
import com.smartbank.demo.service.CityService;

public class CityServiceImpl extends BaseServiceImpl<City, Long> implements CityService {

  CityRepository cityRepository;

  public CityServiceImpl(BaseRepository<City, Long> repository, CityRepository cityRepository) {
    super(repository);
    this.cityRepository = cityRepository;
  }
}
