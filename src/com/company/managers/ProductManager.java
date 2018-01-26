package com.company.managers;

import com.company.entities.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                Product productToAdd = new Product(currentId, names);
                products.add(productToAdd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    public static String getOptimalName(List<String> names) {
        List<String> wordsForCurrentNames = getWordsForCurrentNames(names);

        Map<String,Map<String,Integer>> wordToNextWordToNumberOfWord = getWordToNextWordToNumberOfWord(names, wordsForCurrentNames);

        Map<String,Double> wordToCoef = getWordsToCoef(names, wordsForCurrentNames);
        
        Map<String,Integer> nameToNumberOfDiffWords = getNumberOfDiffrentWords(names);
        
        Map<String,Double> nameToCoef = getNameToCoef(names, wordToCoef, nameToNumberOfDiffWords);

        return getAnswer(nameToCoef);
    }

    private static  Map<String,Map<String,Integer>>  getWordToNextWordToNumberOfWord(List<String> names, List<String> wordsForCurrentNames) {
        Map<String,Map<String,Integer>> wordToNextWordToNumberOfWord = new HashMap<>();
        String[] words;
        for (String word : wordsForCurrentNames) {
            Map<String,Integer> nextWordToNumberOfWord = new HashMap<>();
            for (String name : names) {
                words = name.split("\\s+");
                for (int i = 0; i < words.length -1 ; i++) {
                    if(word.toLowerCase().equals(words[i].toLowerCase())){
                        if(!(nextWordToNumberOfWord.get(words[i + 1].toLowerCase()) == null)) {
                            nextWordToNumberOfWord.put(words[i + 1].toLowerCase(), nextWordToNumberOfWord.get(words[i + 1].toLowerCase()) + 1);
                        } else {
                            nextWordToNumberOfWord.put(words[i + 1].toLowerCase(), 1);
                        }
                    }
                }
            }
            wordToNextWordToNumberOfWord.put(word,nextWordToNumberOfWord);
        }
        return wordToNextWordToNumberOfWord;
    }

    private static String getAnswer(Map<String, Double> nameToCoef) {
        String ans = "";
        double maxCoef = Double.MIN_VALUE;
        for(Map.Entry<String, Double> entry : nameToCoef.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            if(value > maxCoef) {
                ans = key;
                maxCoef = value;
            }
        }
        return ans;
    }

    private static Map<String,Double> getNameToCoef(List<String> names, Map<String, Double> wordToCoef, Map<String, Integer> nameToNumberOfDiffWords) {
        Map<String,Double> nameToCoef = new HashMap<>();
        String[] words;
        for (String name : names) {
            words = name.split("\\s+");
            double coef = 1;
            for (String word : words) {
                coef += wordToCoef.get(word.toLowerCase());
            }
            nameToCoef.put(name, coef * nameToNumberOfDiffWords.get(name));
        }
        return nameToCoef;
    }

    private static Map<String,Integer> getNumberOfDiffrentWords(List<String> names) {
        Map<String,Integer> nameToNumberOfDiffWords = new HashMap<>();
        String[] words;
        for (String name : names) {
            words = name.split("\\s+");
            List<String> wordsForCurrentName = new ArrayList<>();
            for (String word : words) {
                if (!wordsForCurrentName.contains(word.toLowerCase())) {
                    wordsForCurrentName.add(word.toLowerCase());
                }
            }
            nameToNumberOfDiffWords.put(name, wordsForCurrentName.size());
        }
        return nameToNumberOfDiffWords;
    }

    private static Map<String,Double> getWordsToCoef(List<String> names, List<String> wordsForCurrentNames) {
        Map<String,Double> wordToCoef = new HashMap<>();
        String[] words;
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
            wordToCoef.put(currentWord, coef);
        }
        return wordToCoef;
    }

    private static ArrayList<String> getWordsForCurrentNames(List<String> names) {
        ArrayList<String> wordsForCurrentNames = new ArrayList<>();
        String[] words;
        for (String name : names) {
            words = name.split("\\s+");
            for (String word : words) {
                if (!wordsForCurrentNames.contains(word.toLowerCase())) {
                    wordsForCurrentNames.add(word.toLowerCase());
                }
            }
        }
        return wordsForCurrentNames;
    }

    public static String getFeature(Pattern pattern, String optimalName) {
        String feature = "";
        Matcher matcher = pattern.matcher(optimalName);
        if (matcher.find()) {
            feature = matcher.group(0);
        }
        return feature;
    }

}
