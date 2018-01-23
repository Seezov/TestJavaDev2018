package com.company;

import com.company.entities.Product;
import com.company.managers.FileManager;
import com.company.managers.ProductManager;
import org.json.JSONException;
import org.json.JSONArray;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Pattern pattern = Pattern.compile("\\d*[.]*\\d+ *[a-z]+");
        for (Product product : products) {
            String optimalName = ProductManager.getOptimalName(product.getNames());
            String feature = ProductManager.getFeature(pattern, optimalName);
            product.setFeature(feature);
            product.setOptimalName(optimalName);
        }
        FileManager.writeToOutput(products);
    }
}
