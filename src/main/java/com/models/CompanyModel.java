package com.models;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class CompanyModel implements Model {

    private String name;
    private Integer id;
    private ArrayList<AddressModel> addreses = new ArrayList<AddressModel>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressModel[] getAddreses() {
        return addreses.toArray( new AddressModel[addreses.size()]);
    }

    public void addAddress(AddressModel address) {
        this.addreses.add( address );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString(){
        String str = "Company [name=" + name + ", id=" + id;
        for (AddressModel address: addreses) {
            str += ", " + address;
        }
        str += "]";
        return str;
    }

    public DBObject toBSON() {
        BasicDBList addressesList = new BasicDBList();
        for (AddressModel address: addreses) {
            addressesList.add(address.toBSON());
        }
        return new BasicDBObject("name", name).append("id", id).append("addresses", addressesList);
    }
}
