package ru.idarenin.restservice.service;

import org.springframework.stereotype.Service;
import ru.idarenin.restservice.dao.CountryRepository;
import ru.idarenin.restservice.exception.NotFoundException;
import ru.idarenin.restservice.pojo.Country;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public Country get(long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Country> getAll() {
        return new ArrayList<>(repository.findAll());
    }

    public Country add(Country country) {
        country.setId(0); // guarantee create
        return repository.save(country);
    }

    public Country update(long id, Country country) {
        get(id); // exists check
        country.setId(id); //diff id in path&body check
        return repository.save(country);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
