
package com.example.photogallery1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media {
    public Media(String m) {
        this.m = m;
    }

    @SerializedName("m")
    @Expose
    private String m;

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

}
