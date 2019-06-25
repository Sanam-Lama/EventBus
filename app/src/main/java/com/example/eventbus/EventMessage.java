package com.example.eventbus;

/* This is a plain POJO (Plain Old Java Object) java class. */

public class EventMessage {

    private String notification;

    public EventMessage(String notification) {
        this.notification = notification;
    }

    public String getNotification() {
        return notification;
    }
}
