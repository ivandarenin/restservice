package ru.idarenin.restservice.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.idarenin.restservice.exception.NotFoundException;
import ru.idarenin.restservice.pojo.Country;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CountryServiceTest {

    @TestConfiguration
    static class CountryServiceTestConfig{
        @Bean
        public CountryService countryServiceTest(){
            return new CountryService();
        }
    }

    @Autowired
    public CountryService service;

    @Test
    public void add() {
        Country addedCountry = addDefault();

        Assert.assertNotEquals(addedCountry.getId(), 0);
        Assert.assertEquals(addedCountry.getName(), "Russia");
        Assert.assertEquals(addedCountry.getCapital(), "Moscow");

    }

    @Test
    public void getAll() {
        addDefault();
        Assert.assertTrue(service.getAll().size()>0);
    }

    @Test
    public void getById() throws NotFoundException {
        Country addedCountry = addDefault();

        Country country = service.get(addedCountry.getId());
        Assert.assertEquals(country.getId(), addedCountry.getId());
        Assert.assertEquals(country.getName(), "Russia");
        Assert.assertEquals(country.getCapital(), "Moscow");
    }

    @Test
    public void update() throws NotFoundException {
        Country addedCountry = addDefault();

        Country country = new Country();
        country.setName("RF");
        country.setCapital("Yaroslavl");

        Country changed = service.update(addedCountry.getId(), country);
        Assert.assertEquals(changed.getId(), addedCountry.getId());
        Assert.assertEquals(changed.getName(), "RF");
        Assert.assertEquals(changed.getCapital(), "Yaroslavl");
    }

    @Test(expected = NotFoundException.class)
    public void deleteById() {
        Country addedCountry = addDefault();

        service.delete(addedCountry.getId());
        service.get(addedCountry.getId());
    }

    public Country addDefault() {
        Country country = new Country();
        country.setName("Russia");
        country.setCapital("Moscow");

        return service.add(country);
    }
}
