package com.models;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AddressModel implements Model {

    private String country;
    private String city;
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toString(){
        return "Address [country=" + country + ", city=" + city + ", street=" + street +"]";
    }

    public DBObject toBSON() {
        return new BasicDBObject("country", country)
                .append("city", city)
                .append("address", city);
    }
}
