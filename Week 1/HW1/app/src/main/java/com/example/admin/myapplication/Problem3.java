package com.example.admin.myapplication;

public class Problem3 {
    public static void main (String[] args ) {
        palindromeTest("abcd");
        palindromeTest("xyzyx");
        palindromeTest("mystic");
        palindromeTest("dad");
        palindromeTest("abcdba");
        palindromeTest("12345678987654321");
    }

    public static boolean palindromeTest(String input){
        int length = input.length() - 1; //subtract 1 to account for 0 index
        for (int i = 0; i<= length/2; i++){
            if (input.charAt(i) != input.charAt(length - i)){
                System.out.println(input + " IS NOT A PALINDROME!!!");
                return false;
            }
        }
        System.out.println(input + " IS A PALINDROME");
        return true;
    }
}
