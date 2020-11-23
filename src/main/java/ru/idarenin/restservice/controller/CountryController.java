package ru.idarenin.restservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.idarenin.restservice.pojo.Country;
import ru.idarenin.restservice.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Country getCountry(@PathVariable long id) {
        return service.get(id);
    }

    @PostMapping()
    public Country createCountry(@RequestBody Country country) {
        return service.add(country);
    }

    @PutMapping("{id}")
    public Country updateCountry(@PathVariable long id, @RequestBody Country country) {
        return service.update(id, country);
    }

    @DeleteMapping("{id}")
    public void deleteCountry(@PathVariable long id) {
        service.delete(id);
    }
}
