package com.example.admin.googlebookshw.model;

import com.google.gson.annotations.SerializedName;

public class VolumeInfo {
    @SerializedName("title")
    String title;
    @SerializedName("authors")
    String[] authors;

    public VolumeInfo(String title, String[] authors) {
        this.title = title;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }
}
