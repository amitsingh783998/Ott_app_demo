package com.seeksolution.ottplateformdemo.Model;

import java.util.ArrayList;



public class PackageResponse{
    public String code;
    public String message;
    public ArrayList<Packages> data;
    public boolean error;
    public boolean status;

    public PackageResponse(String code, String message, ArrayList<Packages> data, boolean error, boolean status) {
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

    public ArrayList<Packages> getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }

    public boolean isStatus() {
        return status;
    }
}


