
package com.example.android_basic_demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListDatum {

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("url")
    @Expose
    private String url;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
