package com.company.managers;

import com.company.entities.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private static final String INPUT_FILE_PATH = "src/com/company/IOFiles/Input.txt";
    private static final String OUT_FILE_PATH = "src/com/company/IOFiles/Output.txt";

    public static String getJSONFromFile() {
        String json = "";
        try {
            json = new Scanner(new File(INPUT_FILE_PATH)).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static void writeToOutput(List<Product> products) {
        PrintWriter oFile;
        try {
            oFile = new PrintWriter(OUT_FILE_PATH);
            for (Product product : products) {
                oFile.print("ID: " + product.getId() + " Optimal name: " + product.getOptimalName());
                if (!product.getFeature().equals("")) {
                    oFile.println(" Feature: " + product.getFeature());
                } else {
                    oFile.println();
                }
            }
            oFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
