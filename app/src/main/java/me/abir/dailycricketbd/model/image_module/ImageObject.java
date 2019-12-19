package me.abir.dailycricketbd.model.image_module;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageObject implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("album_slug")
    @Expose
    private String albumSlug;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlbumSlug() {
        return albumSlug;
    }

    public void setAlbumSlug(String albumSlug) {
        this.albumSlug = albumSlug;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.src);
        dest.writeString(this.albumSlug);
        dest.writeString(this.createdAt);
    }

    public ImageObject() {
    }

    protected ImageObject(Parcel in) {
        this.title = in.readString();
        this.src = in.readString();
        this.albumSlug = in.readString();
        this.createdAt = in.readString();
    }

    public static final Parcelable.Creator<ImageObject> CREATOR = new Parcelable.Creator<ImageObject>() {
        @Override
        public ImageObject createFromParcel(Parcel source) {
            return new ImageObject(source);
        }

        @Override
        public ImageObject[] newArray(int size) {
            return new ImageObject[size];
        }
    };
}
