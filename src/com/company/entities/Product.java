package com.company.entities;

import java.util.List;

public class Product {

    private int id;
    private List<String> names;
    private String optimalName;
    private String feature;

    public Product(int id, List<String> names) {
        this.id = id;
        this.names = names;
    }

    public int getId() {
        return id;
    }

    public List<String> getNames() {
        return names;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getOptimalName() {
        return optimalName;
    }

    public void setOptimalName(String optimalName) {
        this.optimalName = optimalName;
    }
}
