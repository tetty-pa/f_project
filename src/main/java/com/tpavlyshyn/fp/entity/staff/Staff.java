package com.tpavlyshyn.fp.entity.staff;


import com.tpavlyshyn.fp.entity.Entity;

public class Staff extends Entity {
    private static final long serialVersionUID = -6889036256149495388L;
    String name;
    String surname;
    int positionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", position_id=" + positionId +
                '}';
    }
}
