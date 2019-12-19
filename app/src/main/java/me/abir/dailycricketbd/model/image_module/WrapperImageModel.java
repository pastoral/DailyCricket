package me.abir.dailycricketbd.model.image_module;

import android.os.Parcel;
import android.os.Parcelable;

import me.abir.dailycricketbd.model.image_module_v2.Photo;

/**
 * Created by Abir on 04-Nov-17.
 */

public class WrapperImageModel implements Parcelable {
    private Photo firstImageModel;
    private Photo secondImageModel;

    public Photo getSecondImageModel() {
        return secondImageModel;
    }

    public void setSecondImageModel(Photo secondImageModel) {
        this.secondImageModel = secondImageModel;
    }

    public Photo getFirstImageModel() {
        return firstImageModel;
    }

    public void setFirstImageModel(Photo firstImageModel) {
        this.firstImageModel = firstImageModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.firstImageModel, flags);
        dest.writeParcelable(this.secondImageModel, flags);
    }

    public WrapperImageModel() {
    }

    protected WrapperImageModel(Parcel in) {
        this.firstImageModel = in.readParcelable(ImageObject.class.getClassLoader());
        this.secondImageModel = in.readParcelable(ImageObject.class.getClassLoader());
    }

    public static final Parcelable.Creator<WrapperImageModel> CREATOR = new Parcelable.Creator<WrapperImageModel>() {
        @Override
        public WrapperImageModel createFromParcel(Parcel source) {
            return new WrapperImageModel(source);
        }

        @Override
        public WrapperImageModel[] newArray(int size) {
            return new WrapperImageModel[size];
        }
    };
}
