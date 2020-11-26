package ru.idarenin.restservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.idarenin.restservice.pojo.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
