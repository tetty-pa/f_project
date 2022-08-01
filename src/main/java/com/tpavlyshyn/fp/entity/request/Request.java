package com.tpavlyshyn.fp.entity.request;

import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.entity.Entity;
import com.tpavlyshyn.fp.entity.user.User;

public class Request extends Entity {
    int amount;
    int userId;
    User user;
    int cruiseId;
    Cruise cruise;
    Status status;

    public int getAmount() {return amount;}

    public void setAmount(int amount) {this.amount = amount;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public int getCruiseId() {return cruiseId;}

    public void setCruiseId(int cruiseId) {this.cruiseId = cruiseId;}

    public Cruise getCruise() {return cruise;}

    public void setCruise(Cruise cruise) {this.cruise = cruise;}

    public Status getStatus() {return status;}

    public void setStatus(Status status) {this.status = status;}

    @Override
    public String toString() {
        return "Request{" +
                "amount=" + amount +
                ", userId=" + userId +
                ", user=" + user +
                ", cruiseId=" + cruiseId +
                ", cruise=" + cruise +
                ", status=" + status +
                '}';
    }
}
