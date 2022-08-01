package com.tpavlyshyn.fp.entity;

public class TranslationPort {
    int portId;
    String land;
    String cityLand;
    String countryLand;

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getCityLand() {
        return cityLand;
    }

    public void setCityLand(String cityLand) {
        this.cityLand = cityLand;
    }

    public String getCountryLand() {
        return countryLand;
    }

    public void setCountryLand(String countryLand) {
        this.countryLand = countryLand;
    }
}
