package ru.idarenin.restservice.pojo;

public class Country {

    private long id;
    private String name;
    private String capital;

    public Country(long id, String name, String capital) {
        this.id = id;
        this.name = name;
        this.capital = capital;
    }

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
