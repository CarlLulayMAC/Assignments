package com.example.admin.myapplication;

import java.util.HashMap;

public class Problem2 {

    public static void main(String[] args) {
        HashMap<String, Integer> toPrint = letterCount("aaa bbb c dd eeee");
        System.out.println(toPrint.toString());
    }

    private static HashMap<String, Integer> letterCount(String input) {
        HashMap<String, Integer> table = new HashMap<String, Integer>();
        HashMap<String, Integer> output = new HashMap<String, Integer>();
        int length = input.length();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < length; i++) {
            int count = 0;
            for (int j = 0; j < length; j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    count++;
                    table.put(input.charAt(i) + "", count);
                }
            }
        }
        for (int i = 0; i < alphabet.length(); i++) {
            char letter = alphabet.charAt(i);
            int value;
            if (table.get("" + letter) != null)
                value = table.get("" + letter);
            else
                value = 0;
            output.put("" + letter, value);
        }
        return output;
    }
}
