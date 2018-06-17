package com.example.admin.googlebookshw.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookList {
    @SerializedName("items")
    private ArrayList<Book> bookList;
    @SerializedName("totalItems")
    private int totalItems;

    public int getTotalItems() {
        return totalItems;
    }

    public ArrayList<Book> getBookArrayList() {
        return bookList;
    }
}