package com.example.csi;

public class UserHelperClass2 {
    String id,from,to,student_roll_no,message;

    public UserHelperClass2() {
    }

    public UserHelperClass2(String id, String from, String to, String student_roll_no, String message) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.student_roll_no = student_roll_no;
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

    public String getStudent_roll_no() {
        return student_roll_no;
    }

    public void setStudent_roll_no(String student_roll_no) {
        this.student_roll_no = student_roll_no;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
