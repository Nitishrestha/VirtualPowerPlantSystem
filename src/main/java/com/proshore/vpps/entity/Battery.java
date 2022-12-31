package com.proshore.vpps.entity;

import javax.persistence.*;

@Entity
@Table
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer postcode;
    private Integer capacity;

    public Battery() {
    }

    public Battery(String name, Integer postCode, Integer capacity) {
        this.name = name;
        this.postcode = postCode;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postCode) {
        this.postcode = postCode;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
