package com.tpavlyshyn.fp.entity.user;

public enum Role {
    ADMIN, CLIENT;


    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
