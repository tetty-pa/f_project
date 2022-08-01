package com.tpavlyshyn.fp.entity.request;

import com.tpavlyshyn.fp.entity.user.Role;
import com.tpavlyshyn.fp.entity.user.User;

public enum Status {
    OPENED, CONFIRMED, PAID, CLOSED;

 /*   public static Status getStatus(Request request) {
        int statusId = request.getStatusId();
        return Status.values()[statusId-1];
    }

    public String getName() {
        return name().toLowerCase();
    }*/
}
