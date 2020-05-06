package com.example.csi;

public class UserHelperClass {
    String student_name,parent_name,roll_no,mobile,email,address,fa_email,warden_email,password;

    public UserHelperClass() {

    }

    public UserHelperClass(String student_name, String parent_name, String roll_no, String mobile, String email, String address, String fa_email, String warden_email, String password) {
        this.student_name = student_name;
        this.parent_name = parent_name;
        this.roll_no = roll_no;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.fa_email = fa_email;
        this.warden_email = warden_email;
        this.password = password;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFa_email() {
        return fa_email;
    }

    public void setFa_email(String fa_email) {
        this.fa_email = fa_email;
    }

    public String getWarden_email() {
        return warden_email;
    }

    public void setWarden_email(String warden_email) {
        this.warden_email = warden_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
