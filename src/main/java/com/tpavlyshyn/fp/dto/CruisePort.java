package com.tpavlyshyn.fp.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class CruisePort {

    int cruiseId;
    int portId;
    int sequenceNumber;
    Timestamp arrivalTime;

    public CruisePort( int portId, int sequenceNumber, Timestamp arrivalTime) {
        this.portId = portId;
        this.sequenceNumber = sequenceNumber;
        this.arrivalTime = arrivalTime;
    }

    public int getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return sequenceNumber +
                ") Port №" + portId +
                ", arrivalTime=" + arrivalTime;
    }
}
