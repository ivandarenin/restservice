package ru.idarenin.restservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.idarenin.restservice.pojo.Country;
import ru.idarenin.restservice.service.CountryService;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CountryService service;

    @Test
    public void add() throws Exception {
        Country country = new Country();
        country.setName("Russia");
        country.setCapital("Moscow");

        mockMvc.perform(
                post("/countries")
                        .content(objectMapper.writeValueAsString(country))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        ;
    }

    @Test
    public void getAll() throws Exception {
        Country country = new Country();
        country.setName("Russia");
        country.setCapital("Moscow");

        Mockito.when(service.getAll()).thenReturn(Collections.singletonList(country));
        mockMvc.perform(
                get("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Russia"))
                .andExpect(jsonPath("$[0].capital").value("Moscow"))
        ;
    }

    @Test
    public void edit() throws Exception {
        Country country = new Country();
        country.setName("Russia");
        country.setCapital("Yaroslavl");

        Mockito.when(service.update(Mockito.anyLong(), Mockito.any())).thenReturn(country);
        mockMvc.perform(
                put("/countries/1")
                        .content(objectMapper.writeValueAsString(country))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("capital").value("Yaroslavl"))
        ;
    }
}
