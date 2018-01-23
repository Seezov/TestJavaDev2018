package com.company;

import com.company.entities.Product;
import com.company.managers.FileManager;
import com.company.managers.ProductManager;
import org.json.JSONException;
import org.json.JSONArray;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String jsonString = FileManager.getJSONFromFile();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            System.out.println("Can't initialize JSONArray");
        }
        List<Product> products = ProductManager.getListOfProducts(jsonArray);
    }
}
