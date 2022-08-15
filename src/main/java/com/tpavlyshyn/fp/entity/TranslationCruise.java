package com.tpavlyshyn.fp.entity;

public class TranslationCruise {
    int cruiseId;
    String land;
    String cruiseNameLand;
    String descriptionLand;

    public TranslationCruise(int cruiseId, String land, String cruiseNameLand, String descriptionLand) {
        this.cruiseId = cruiseId;
        this.land = land;
        this.cruiseNameLand = cruiseNameLand;
        this.descriptionLand = descriptionLand;
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

    public String getCruiseNameLand() {
        return cruiseNameLand;
    }

    public void setCruiseNameLand(String cruiseNameLand) {
        this.cruiseNameLand = cruiseNameLand;
    }

    public String getDescriptionLand() {
        return descriptionLand;
    }

    public void setDescriptionLand(String descriptionLand) {
        this.descriptionLand = descriptionLand;
    }
}
