package com.example.csi;

public class UserHelperClass3 {
    private String from,to,message,id;

    public UserHelperClass3() {

    }

    public UserHelperClass3(String from, String to, String message, String id) {
        this.from = from;
        this.to = to;
        this.message = message;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
