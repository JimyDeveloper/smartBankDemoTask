package com.smartbank.demo.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "cities",
    indexes = {
        @Index(columnList = "name", name = "city_name_idx")})
public class City implements Serializable, Comparable<City> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column(name = "code")
  long code;
  @Column(name = "name")
  String name;

  public City(long code, String name) {
    this.code = code;
    this.name = name;
  }

  @Override
  public int compareTo(City o) {
    if (Objects.isNull(this.getName()) && Objects.isNull(o.getName())) {
      return -1;
    }

    return this.getName().compareTo(o.getName());
  }
}
