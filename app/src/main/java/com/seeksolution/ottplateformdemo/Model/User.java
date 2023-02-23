package com.seeksolution.ottplateformdemo.Model;

public class User{
    public String id;
    public String name;
    public String email;
    public String password;
    public String mobile;
    public String package_id;
    public String date;
    public String time;
    public String created_by;
    public String token;
    public String status;

    public User(String id, String name, String email, String password, String mobile, String package_id, String date, String time, String created_by, String token, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.package_id = package_id;
        this.date = date;
        this.time = time;
        this.created_by = created_by;
        this.token = token;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPackageId() {
        return package_id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getToken() {
        return token;
    }

    public String getStatus() {
        return status;
    }
}









