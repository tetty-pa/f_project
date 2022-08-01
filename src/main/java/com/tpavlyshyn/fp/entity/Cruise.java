package com.tpavlyshyn.fp.entity;

import java.util.Date;
import java.util.List;

public class Cruise extends Entity {
    private static final long serialVersionUID = -6889036256149495388L;
    int linerId;
    int price;
    int numberOfPorts;
    String cruiseName;
    String description;
    Date startDate;
    Date endDate;
    String cruisePhoto;
    Liner liner;


    List<Port> portList;

    public Cruise() {
    }

    public Cruise(int price, int numberOfPorts, String cruiseName, String description, Date startDate, Date endDate) {
        this.price = price;
        this.numberOfPorts = numberOfPorts;
        this.cruiseName = cruiseName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public String getCruiseName() {
        return cruiseName;
    }

    public void setCruiseName(String cruiseName) {
        this.cruiseName = cruiseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getLinerId() {
        return linerId;
    }

    public void setLinerId(int linerId) {
        this.linerId = linerId;
    }

    public int getNumberOfPorts() {
        return numberOfPorts;
    }

    public void setNumberOfPorts(int numberOfPorts) {
        this.numberOfPorts = numberOfPorts;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCruisePhoto() {
        return cruisePhoto;
    }

    public void setCruisePhoto(String cruisePhoto) {
        this.cruisePhoto = cruisePhoto;
    }

    public Liner getLiner() {
        return liner;
    }

    public void setLiner(Liner liner) {
        this.liner = liner;
    }

    public List<Port> getPortList() {
        return portList;
    }

    public void setPortList(List<Port> portList) {
        this.portList = portList;
    }

    @Override
    public String toString() {
        return "Cruise{" +
                "linerId=" + linerId +
                ", price=" + price +
                ", numberOfPorts=" + numberOfPorts +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cruisePhoto='" + cruisePhoto + '\'' +
                ", liner=" + liner +
                '}';
    }
}
