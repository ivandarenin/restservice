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
    public void serviceMethodsTests() throws NotFoundException {
        Country country = new Country();
        country.setName("Russia");
        country.setCapital("Moscow");

        service.add(country);
        Long id = country.getId();
        Assert.assertNotNull(id);

        getAll();
        getById(id);
        update(country);
        deleteById(id);
    }

    public void getAll() {
        Assert.assertTrue(service.getAll().size()>0);
    }

    public void getById(Long id) throws NotFoundException {
        Assert.assertNotNull(service.get(id));
    }

    public void update(Country country) throws NotFoundException {
        country.setCapital("Yaroslavl");
        service.update(country.getId(), country);
        Assert.assertEquals("Yaroslavl", service.get(country.getId()).getCapital());
    }

    public void deleteById(Long id) {
        service.delete(id);
    }
}
