package com.tpavlyshyn.fp.entity;

public class LinerHasStaff {
    int linerId;
    int staffId;

    public int getLinerId() {
        return linerId;
    }

    public void setLinerId(int linerId) {
        this.linerId = linerId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "LinerHasStaff{" +
                "linerId=" + linerId +
                ", staffId=" + staffId +
                '}';
    }
}
