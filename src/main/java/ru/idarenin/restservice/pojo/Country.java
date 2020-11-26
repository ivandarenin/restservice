package ru.idarenin.restservice.pojo;

import javax.persistence.*;

@Entity
public class Country {

    @Id
    @SequenceGenerator( name = "id_sequence", sequenceName = "id_sequence", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private long id;
    private String name;
    private String capital;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
