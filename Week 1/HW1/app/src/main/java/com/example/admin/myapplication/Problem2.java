package com.example.admin.myapplication;

import java.util.HashMap;
import java.util.Set;

public class Problem2 {
    public static void main (String[] args ) {
        letterCount("abbcccddddeeeeeffffff");
        letterCount("adbfedfbeffecfdefdcec");
    }

    public static Object letterCount(String input){
        HashMap<String,Integer> table = new HashMap<String,Integer>();
        int length = input.length();
        StringBuilder sb = new StringBuilder(input);

        for (int i = 0; i < length; i++){
            int count = 0;
            for (int j = 0; j < length; j++){
                if (input.charAt(i) == input.charAt(j)){
                    count++;
                    table.put(input.charAt(i) + "", count);
                }
            }
        }
        Set<String> keys = table.keySet();
        for (String key : keys){
            int value = table.get(key);
            if (value > 1)
                System.out.println(key + "=" + value);
        }
    }
    return
}