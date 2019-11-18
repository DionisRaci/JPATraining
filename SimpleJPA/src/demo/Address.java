package demo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(AddressId.class)
public class Address {
    @Id
    private String SSN;
    @Id
    private Integer address_no;
    private String country;
    private String city;
    private String street;
    private Integer street_no;
    @ManyToOne()
    @JoinColumn(name = "SSN", insertable = false, updatable = false)
    private Person person;

    public Address(Integer address_no, String country, String city, String street, Integer street_no, Person person) {
        this.address_no = address_no;
        this.country = country;
        this.city = city;
        this.street = street;
        this.street_no = street_no;
        this.person = person;
        this.SSN = person.getsSSN();
    }

    public Address() {

    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public Integer getAddress_no() {
        return address_no;
    }

    public void setAddress_no(Integer address_no) {
        this.address_no = address_no;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreet_no() {
        return street_no;
    }

    public void setStreet_no(Integer street_no) {
        this.street_no = street_no;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
