package com.example.admin.myapplication;

public class Problem1 {
    public static void main(String[] args) {
        String input = "acp"; //output should be zxk
        System.out.println(encode(input));
    }

    private static String encode(String input) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] output = input.toCharArray();
        String out = input;
        for (int i = 0; i < input.length(); i++) {
            int charLocation = alphabet.indexOf(input.charAt(i));
            int reverseLocation = alphabet.length() - charLocation;
            char reverseChar = alphabet.charAt(reverseLocation -1);
            output[i] = reverseChar;

        }
        return String.valueOf(output);
    }

}