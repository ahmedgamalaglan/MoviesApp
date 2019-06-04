package com.gemy.ahmed.tmas2.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Review {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("results")
    @Expose
    private List<Reviews> reviews = new ArrayList<>();
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("total_results")
    @Expose
    private int totalResults;

    public int getId() {
        return id;
    }


    public int getPage() {
        return page;
    }


    public List<Reviews> getReviews() {
        return reviews;
    }


    public int getTotalPages() {
        return totalPages;
    }


    public int getTotalResults() {
        return totalResults;
    }


    public class Reviews {

        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("url")
        @Expose
        private String url;

        public String getAuthor() {
            return author;
        }


        public String getContent() {
            return content;
        }


        public String getId() {
            return id;
        }


        public String getUrl() {
            return url;
        }


    }
}
