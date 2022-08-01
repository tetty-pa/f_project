package com.tpavlyshyn.fp.entity.user;


import com.tpavlyshyn.fp.entity.Entity;

public class User extends Entity {
    private static final long serialVersionUID = -6889036256149495388L;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String urlDocument;
    private int roleId ;

    public User() {
    }

    public User(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User(String login, String password, String name, String surname, String urlDocument, int roleId) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.urlDocument = urlDocument;
        this.roleId = roleId;
    }

    public String getLogin() { return login;}

    public void setLogin(String login) { this.login = login;}

    public String getPassword() { return password;}

    public void setPassword(String password) { this.password = password;}

    public String getName() { return name; }

    public void setName(String name) { this.name = name;}

    public void setSurname(String surname) { this.surname = surname;}

    public String getSurname() { return surname;}

    public String getUrlDocument() { return urlDocument;}

    public void setUrlDocument(String urlDocument) { this.urlDocument = urlDocument;}

    public int getRoleId() { return roleId;}

    public void setRoleId(int roleId) {this.roleId = roleId;}

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", url_document='" + urlDocument + '\'' +
                ", role=" + roleId +
                '}';
    }
}

