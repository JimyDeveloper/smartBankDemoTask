package com.smartbank.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T, ID> {
  Page<T> findAll(Pageable pageable);

  T save(T entity);

  T findById(ID id);

  void delete(T entity);

  void deleteById(ID id);

  long count();
}
