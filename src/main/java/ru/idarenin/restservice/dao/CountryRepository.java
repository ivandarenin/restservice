package ru.idarenin.restservice.dao;

import org.springframework.data.repository.CrudRepository;
import ru.idarenin.restservice.pojo.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
