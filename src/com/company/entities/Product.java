package com.company.entities;

import java.util.List;

public class Product {

    private int id;
    private List<String> names;

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
}
