package com.example.csi;

public class UserHelperClass2 {
    String id,from,to,message;

    public UserHelperClass2() {
    }

    public UserHelperClass2(String id, String from, String to, String message) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
