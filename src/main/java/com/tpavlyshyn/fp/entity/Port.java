package com.tpavlyshyn.fp.entity;

public class Port extends Entity {
    int cruiseId;
    String land;
    String city;
    String country;

    public Port(int cruiseId, String land, String city, String country) {
        this.cruiseId = cruiseId;
        this.land = land;
        this.city = city;
        this.country = country;
    }

    public Port() {
    }

    public int getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
    }

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
