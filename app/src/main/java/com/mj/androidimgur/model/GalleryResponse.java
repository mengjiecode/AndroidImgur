package com.mj.androidimgur.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mengjie.huang on 30/8/2018.
 */

public class GalleryResponse {
    @SerializedName("data")
    @Expose
    private List<GalleryItem> data = null;

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("status")
    @Expose
    private long status;

    public List<GalleryItem> getData() {
        return data;
    }

    public void setData(List<GalleryItem> data) {
        this.data = data;
    }

    public GalleryResponse withData(List<GalleryItem> data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public GalleryResponse withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public GalleryResponse withStatus(long status) {
        this.status = status;
        return this;
    }

}
