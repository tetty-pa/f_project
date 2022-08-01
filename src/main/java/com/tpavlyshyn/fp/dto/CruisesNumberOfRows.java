package com.tpavlyshyn.fp.dto;

import com.tpavlyshyn.fp.entity.Cruise;

import java.util.List;

public class CruisesNumberOfRows {
    List<Cruise> cruises;
    int numberOfRows;

    public List<Cruise> getCruises() {
        return cruises;
    }

    public void setCruises(List<Cruise> cruises) {
        this.cruises = cruises;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }
}
