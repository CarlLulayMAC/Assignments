package com.example.admin.googlebookshw.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;
    public static final String TAG = Book.class.getSimpleName() + "_TAG";

    public String getTitle() {
        return volumeInfo.title;
    }

    public String[] getAuthors() {
        return volumeInfo.authors;
    }

    public String getThumbnailUrl() {
        if (volumeInfo.imageLinks == null || volumeInfo.imageLinks.thumbnail == null)
            return "https://imgur.com/tQoPNQe"; // This is a thumbnail placeholder
        return volumeInfo.imageLinks.thumbnail;
    }

    public Book(String id, VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public class VolumeInfo {
        @SerializedName("title")
        String title;
        @SerializedName("authors")
        String[] authors;
        @SerializedName("imageLinks")
        ImageLinks imageLinks;

        public VolumeInfo(String title, String[] authors, ImageLinks imageLinks) {
            this.title = title;
            this.authors = authors;
            this.imageLinks = imageLinks;
        }

        private class ImageLinks {
            @SerializedName("smallThumbnail")
            String smallThumbnailLink;
            @SerializedName("thumbnail")
            String thumbnail;

            public ImageLinks(String smallThumbnailLink, String thumbnail) {
                this.smallThumbnailLink = smallThumbnailLink;
                this.thumbnail = thumbnail;
            }
        }
    }
}
