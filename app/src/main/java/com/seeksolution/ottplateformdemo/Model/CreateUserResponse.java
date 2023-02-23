package com.seeksolution.ottplateformdemo.Model;


import java.util.ArrayList;

public class CreateUserResponse{
    public String code;
    public String message;
    public ArrayList<User> data;
    public boolean error;
    public boolean status;

    public CreateUserResponse(String code, String message, ArrayList<User> data, boolean error, boolean status) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.error = error;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<User> getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }

    public boolean isStatus() {
        return status;
    }
}