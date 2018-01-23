package com.company.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {

    private static final String FILE_PATH = "src/com/company/IOFiles/Input.txt";

    public static String getJSONFromFile() {
        String json = "";
        try {
            json = new Scanner(new File(FILE_PATH)).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return json;
    }
}
