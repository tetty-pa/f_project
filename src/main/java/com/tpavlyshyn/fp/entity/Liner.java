package com.tpavlyshyn.fp.entity;

public class Liner extends Entity{
    private static final long serialVersionUID = -6889036256149495388L;
    int passengerCapacity;
    String linerName;
    int companyId;
    String linerPhoto;

    public int getPassengerCapacity() { return passengerCapacity;}

    public void setPassengerCapacity(int passengerCapacity) {this.passengerCapacity = passengerCapacity;}

    public int getCompanyId() {return companyId;}

    public void setCompanyId(int companyId) {this.companyId = companyId;}

    public String getLinerName() {
        return linerName;
    }

    public void setLinerName(String linerName) {
        this.linerName = linerName;
    }

    public String getLinerPhoto() {return linerPhoto;}

    public void setLinerPhoto(String linerPhoto) {this.linerPhoto = linerPhoto;}

    @Override
    public String toString() {
        return "Liner{" +
                "passengerCapacity=" + passengerCapacity +
                ", name='" + linerName + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
