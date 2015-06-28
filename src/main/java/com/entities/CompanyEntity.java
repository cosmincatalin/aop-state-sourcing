package com.entities;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public  class CompanyEntity implements java.io.Serializable {

    private Integer id;
    private String name;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", unique = false, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "Company [name=" + name + ", id=" + id + "]";
    }

}
