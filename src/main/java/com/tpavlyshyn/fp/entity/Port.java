package com.tpavlyshyn.fp.entity;

public class Port extends Entity {
    String land;
    String city;
    String country;


    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "[" + city + "," + country + "]";
    }
}
