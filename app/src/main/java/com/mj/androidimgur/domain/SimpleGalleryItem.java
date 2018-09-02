package com.mj.androidimgur.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.mj.androidimgur.model.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mengjie.huang on 31/8/2018.
 */

public class SimpleGalleryItem implements Parcelable {
    private List<Image> images = null;
    private String link;
    private String title;
    private boolean isAlbum;
    private String type;
    private int imagesCount;

    public SimpleGalleryItem() {

    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAlbum() {
        return isAlbum;
    }

    public void setAlbum(boolean album) {
        isAlbum = album;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImagesCount() {
        return imagesCount;
    }

    public void setImagesCount(int imagesCount) {
        this.imagesCount = imagesCount;
    }

    protected SimpleGalleryItem(Parcel in) {
        if (in.readByte() == 0x01) {
            images = new ArrayList<Image>();
            in.readList(images, Image.class.getClassLoader());
        } else {
            images = null;
        }
        link = in.readString();
        title = in.readString();
        isAlbum = in.readByte() != 0x00;
        type = in.readString();
        imagesCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (images == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(images);
        }
        dest.writeString(link);
        dest.writeString(title);
        dest.writeByte((byte) (isAlbum ? 0x01 : 0x00));
        dest.writeString(type);
        dest.writeInt(imagesCount);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SimpleGalleryItem> CREATOR = new Parcelable.Creator<SimpleGalleryItem>() {
        @Override
        public SimpleGalleryItem createFromParcel(Parcel in) {
            return new SimpleGalleryItem(in);
        }

        @Override
        public SimpleGalleryItem[] newArray(int size) {
            return new SimpleGalleryItem[size];
        }
    };
}