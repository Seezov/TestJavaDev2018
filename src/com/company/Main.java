package com.company;

import com.company.entities.Product;
import com.company.managers.FileManager;
import com.company.managers.ProductManager;
import org.json.JSONException;
import org.json.JSONArray;

import java.util.List;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        // Reading data from JSON
        String jsonString = FileManager.getJSONFromFile();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            System.out.println("Can't initialize JSONArray");
        }
        // Using JSON data to create a list of products
        List<Product> products = ProductManager.getListOfProducts(jsonArray);
        // Using RegEx to find feature
        Pattern pattern = Pattern.compile("\\d*[.]*\\d+ *[a-z]+");
        for (Product product : products) {
            // Finding optimal name
            String optimalName = ProductManager.getOptimalName(product.getNames());
            System.out.println(optimalName);
            // Getting feature from optimal name
            String feature = ProductManager.getFeature(pattern, optimalName);
            product.setFeature(feature);
            product.setOptimalName(optimalName);
        }
        // Writing info to output file
        FileManager.writeToOutput(products);
    }
}
