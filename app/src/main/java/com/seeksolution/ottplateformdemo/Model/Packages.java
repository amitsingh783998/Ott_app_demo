package com.seeksolution.ottplateformdemo.Model;

public class Packages{
    public String id;
    public String package_name;
    public String package_price  ;
    public String package_duration;
    public String package_desc;
    public String package_pic;

    public Packages(String id, String package_name, String package_price, String package_duration, String package_desc, String package_pic) {
        this.id = id;
        this.package_name = package_name;
        this.package_price = package_price;
        this.package_duration = package_duration;
        this.package_desc = package_desc;
        this.package_pic = package_pic;
    }

    public String getId() {
        return id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public String getPackage_price() {
        return package_price;
    }

    public String getPackage_duration() {
        return package_duration;
    }

    public String getPackage_desc() {
        return package_desc;
    }

    public String getPackage_pic() {
        return package_pic;
    }
}
