package com.essue.movieinfo;

import android.os.Parcel;
import android.os.Parcelable;

public class UserComment implements Parcelable {
    private String comment;
    private int recommend;
    private double rating;

    public UserComment(String comment, int recommend, double rating) {
        this.comment = comment;
        this.recommend = recommend;
        this.rating = rating;
    }

    public UserComment(Parcel src){
        recommend = src.readInt();
        comment = src.readString();
        rating = src.readDouble();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public UserComment createFromParcel(Parcel src){
            return new UserComment(src);
        }

        public UserComment[] newArray(int size){
            return new UserComment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(recommend);
        parcel.writeString(comment);
        parcel.writeDouble(rating);
    }
}
