package com.tpavlyshyn.fp.entity.staff;

import com.tpavlyshyn.fp.entity.Entity;

public class Position extends Entity {
    private static final long serialVersionUID = -6889036256149495388L;

    String name;

    public String getName() { return name;}

    public void setName(String name) {this.name = name;}
}
