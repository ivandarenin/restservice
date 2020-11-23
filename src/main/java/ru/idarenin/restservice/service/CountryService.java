package ru.idarenin.restservice.service;

import org.springframework.stereotype.Component;
import ru.idarenin.restservice.exception.NotFoundException;
import ru.idarenin.restservice.pojo.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CountryService {

    private List<Country> countries = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public Country get(long id){
        return countries.stream()
                .filter(country -> country.getId() == id)
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public List<Country> getAll() {
        return countries;
    }

    public Country add(Country country) {
        country.setId(counter.incrementAndGet());
        countries.add(country);

        return country;
    }

    public Country update(long id, Country country) {
        Country countryFromDB = get(id);

        countryFromDB.setName(country.getName());
        countryFromDB.setCapital(country.getCapital());

        return countryFromDB;
    }

    public void delete(long id) {
        countries.removeIf(country -> country.getId() == id);
    }
}
