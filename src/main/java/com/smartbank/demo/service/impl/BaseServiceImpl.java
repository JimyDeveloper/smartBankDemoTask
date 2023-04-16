package com.smartbank.demo.service.impl;

import com.smartbank.demo.model.exception.NotFoundException;
import com.smartbank.demo.repos.BaseRepository;
import com.smartbank.demo.service.BaseService;
import java.lang.reflect.ParameterizedType;
import java.util.function.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BaseServiceImpl<T,ID> implements BaseService<T, ID> {

  private final BaseRepository<T, ID> repository;

  public BaseServiceImpl(BaseRepository<T, ID> repository) {
    this.repository = repository;
  }

  @Override
  public Page<T> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public T save(T entity) {
    return repository.save(entity);
  }

  @Override
  public T findById(ID id) {
    String entityName =
        ((Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    return repository.findById(id).orElseThrow(notFoundExceptionThrow(entityName, entityName + " is not found with given id:" + id));
  }

  @Override
  public void delete(T entity) {
    repository.delete(entity);
  }

  @Override
  public void deleteById(ID id) {
    repository.deleteById(id);
  }

  @Override
  public long count() {
    return repository.count();
  }

  protected Supplier<NotFoundException> notFoundExceptionThrow(String entityName) {
    return this.notFoundExceptionThrow(entityName, null);
  }

  protected Supplier<NotFoundException> notFoundExceptionThrow(String entityName, String developerMessage) {
    return () ->
        new NotFoundException(
            "entity.not.found",
            developerMessage
        );
  }
}
