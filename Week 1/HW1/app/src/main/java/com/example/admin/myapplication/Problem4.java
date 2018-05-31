package com.example.admin.myapplication;

public class Problem4 {
    public static void main (String[] args ) {
        reverse("12345");
        System.out.print("\n");
        reverse("dog");
        System.out.print("\n");
        reverse("cat");
    }

    public static void reverse(String input){
        if (input.length() > 0) {
            System.out.print(input.charAt(input.length()-1));
            input = input.substring(0, input.length()-1);
            reverse(input);
        }
    }
}
