package com.sanketshah.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsFeedWrapper {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<FeedData> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FeedData> getRows() {
        return rows;
    }

    public void setRows(List<FeedData> rows) {
        this.rows = rows;
    }
}
