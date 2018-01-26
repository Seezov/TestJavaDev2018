package com.company.managers;

import com.company.entities.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
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


        /*ProductManager pm = new ProductManager();
        names.sort(pm);
        char first = Character.toUpperCase(names.get(names.size() / 2).charAt(0));
        String ans = first + names.get(names.size() / 2).substring(1);*/
        List<String> wordsForCurrentNames = new ArrayList<>();
        String[] words;

        Map<String,Double> wordCoefMap = new HashMap<>();
        for (String name : names) {
            words = name.split("\\s+");
            for (String word : words) {
                if (!wordsForCurrentNames.contains(word.toLowerCase())) {
                    wordsForCurrentNames.add(word.toLowerCase());
                }
            }
        }
        for (String currentWord : wordsForCurrentNames) {
            int numberOfEnters = 0;
            for (String name : names) {
                words = name.split("\\s+");
                for (String word : words) {
                    if (currentWord.equals(word.toLowerCase())) {
                        numberOfEnters++;
                    }
                }
            }
            double coef = numberOfEnters / (double)names.size();
            wordCoefMap.put(currentWord, coef);
        }
        Map<String,Integer> nameNumberOfDiffWords = new HashMap<>();
        for (String name : names) {
            words = name.split("\\s+");
            List<String> wordsForCurrentName = new ArrayList<>();
            for (String word : words) {
                if (!wordsForCurrentName.contains(word.toLowerCase())) {
                    wordsForCurrentName.add(word.toLowerCase());
                }
            }
            nameNumberOfDiffWords.put(name, wordsForCurrentName.size());
        }
        Map<String,Double> nameCoefMap = new HashMap<>();
        for (String name : names) {
            words = name.split("\\s+");
            double coef = 1;
            for (String word : words) {
                coef += wordCoefMap.get(word.toLowerCase());
            }
            nameCoefMap.put(name, coef * nameNumberOfDiffWords.get(name));
        }
        String ans = null;
        double maxCoef = Double.MIN_VALUE;
        for(Map.Entry<String, Double> entry : nameCoefMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            if(value > maxCoef){
                ans = key;
                maxCoef = value;
            }
            // do what you have to do here
            // In your case, another loop.
        }
        return ans;
    }

    private static void printWords(List<String> wordsForCurrentNames) {
        for (String wordsForCurrentName : wordsForCurrentNames) {
            System.out.print(wordsForCurrentName + " ");
        }
        System.out.println();
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
