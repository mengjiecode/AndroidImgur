package com.mj.androidimgur.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mengjie.huang on 30/8/2018.
 */

public class Image implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private Object title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("width")
    @Expose
    private long width;

    @SerializedName("height")
    @Expose
    private long height;

    @SerializedName("size")
    @Expose
    private long size;

    @SerializedName("link")
    @Expose
    private String link;

    public Image() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    protected Image(Parcel in) {
        id = in.readString();
        title = (Object) in.readValue(Object.class.getClassLoader());
        description = in.readString();
        type = in.readString();
        width = in.readLong();
        height = in.readLong();
        size = in.readLong();
        link = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeValue(title);
        dest.writeString(description);
        dest.writeString(type);
        dest.writeLong(width);
        dest.writeLong(height);
        dest.writeLong(size);
        dest.writeString(link);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

}
