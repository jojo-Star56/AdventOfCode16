package com.jojo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<String> load(String location){
        List<String> output = new ArrayList<String>();

        File file = new File(location);
        BufferedReader buffer;
        try {
            buffer = new BufferedReader(new FileReader(file));

        }catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            return output;
        }

        try {
            String input = buffer.readLine();
            while (input != null) {
                output.add(input);
                input = buffer.readLine();
            }
            buffer.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }
}
