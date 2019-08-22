package com.demo.myapplication.network.response;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "movies")
public class Movies {

    @ColumnInfo(name = "total_pages")
    @SerializedName(value = "total_pages")
    private int totalPages;
    @PrimaryKey()
    @ColumnInfo(name = "id")
    @SerializedName(value = "id")
    private Integer id;
    @ColumnInfo(name = "title")
    @SerializedName(value = "title")
    private String title;
    @ColumnInfo(name = "poster_path")
    @SerializedName(value = "poster_path")
    private String posterPath;
    @ColumnInfo(name = "overview")
    @SerializedName(value = "overview")
    private String overview;

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

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
