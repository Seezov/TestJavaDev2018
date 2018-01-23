package com.company.managers;

import com.company.entities.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    public static List<Product> getListOfProducts(JSONArray jsonArray) {
        List<Product> products = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject currentJSONObj = jsonArray.getJSONObject(i);
                int currentId = currentJSONObj.getInt("id");
                JSONArray currentArrayOfNames = currentJSONObj.getJSONArray("names");
                List<String> names = new ArrayList<>();
                for (int j = 0; j < currentArrayOfNames.length(); j++) {
                    names.add(currentArrayOfNames.getString(j));
                }
                Product productToAdd = new Product(currentId,names);
                products.add(productToAdd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return products;
    }
}
