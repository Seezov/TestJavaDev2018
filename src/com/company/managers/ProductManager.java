package com.company.managers;

import com.company.entities.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductManager implements Comparator<String> {

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
                Product productToAdd = new Product(currentId, names);
                products.add(productToAdd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    public static String getOptimalName(List<String> names) {
        ProductManager pm = new ProductManager();
        names.sort(pm);
        char first = Character.toUpperCase(names.get(names.size() / 2).charAt(0));
        return first + names.get(names.size() / 2).substring(1);
    }

    public static String getFeature(Pattern pattern, String optimalName) {
        String feature = "";
        Matcher matcher = pattern.matcher(optimalName);
        if (matcher.find()) {
            feature = matcher.group(0);
        }
        return feature;
    }

    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}
