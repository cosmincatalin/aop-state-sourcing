package com.entities;

import javax.persistence.*;

@Entity
@Table(name = "companies_addresses")
public class CompanyAddressEntity implements java.io.Serializable  {

    private String country;
    private String city;
    private String street;
    private CompanyEntity company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @Id
    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toString(){
        return "CompanyAddress [company=" + company.getId() +", country=" + country + ", city=" + city + ", street=" + street + "]";
    }
}
