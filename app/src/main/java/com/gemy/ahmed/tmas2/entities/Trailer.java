package com.gemy.ahmed.tmas2.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Trailer {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("results")
    @Expose
    private List<Trailers> trailers = new ArrayList<>();

    public int getId() {
        return id;
    }

    public List<Trailers> getTrailers() {
        return trailers;
    }

    public class Trailers {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("iso_639_1")
        @Expose
        private String iso6391;
        @SerializedName("iso_3166_1")
        @Expose
        private String iso31661;
        @SerializedName("key")
        @Expose
        private String key;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("site")
        @Expose
        private String site;
        @SerializedName("size")
        @Expose
        private int size;
        @SerializedName("type")
        @Expose
        private String type;

        public String getId() {
            return id;
        }

        public String getIso6391() {
            return iso6391;
        }

        public String getIso31661() {
            return iso31661;
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String getSite() {
            return site;
        }

        public int getSize() {
            return size;
        }

        public String getType() {
            return type;
        }

    }


}

