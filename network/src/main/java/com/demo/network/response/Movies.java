package com.demo.network.response;

import com.google.gson.annotations.SerializedName;

public class Movies {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @SerializedName(value = "total_pages")
    private int totalPages;

    @SerializedName(value = "id")
    private Integer id;

    @SerializedName(value = "title")
    private String title;

    @SerializedName(value = "poster_path")
    private String posterPath;

    @SerializedName(value = "overview")
    private String overview;

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
