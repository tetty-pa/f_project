package com.tpavlyshyn.fp.entity;

import java.util.Date;

public class Company extends Entity{
    private static final long serialVersionUID = -6889036256149495388L;
    String name;
    Date foundationDate;

    public String getName() {return name; }

    public void setName(String name) { this.name = name;}

    public Date getFoundationDate() { return foundationDate;}

    public void setFoundationDate(Date foundationDate) { this.foundationDate = foundationDate;}



    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", foundation_date=" + foundationDate +
                '}';
    }
}
