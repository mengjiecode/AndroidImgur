package com.mj.androidimgur.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mengjie.huang on 30/8/2018.
 */

public class GalleryItem {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("link")
    @Expose
    private String link;


    @SerializedName("is_album")
    @Expose
    private boolean isAlbum;

    @SerializedName("images_count")
    @Expose
    private int imagesCount;

    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isAlbum() {
        return isAlbum;
    }

    public void setAlbum(boolean album) {
        isAlbum = album;
    }

    public int getImagesCount() {
        return imagesCount;
    }

    public void setImagesCount(int imagesCount) {
        this.imagesCount = imagesCount;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}
