package com.example.chuwenbin.aidldemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chuwenbin on 2018/3/30.
 */

public class GirlFriend implements Parcelable {

    private String name;
    private int type;

    public static final int TYPE_BEAUTY = 0x20;
    public static final int TYPE_CUTE = 0x21;
    public static final int TYPE_SEXY = 0x22;

    public GirlFriend(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getTypeDesc() {
        if (type == TYPE_BEAUTY) {
            return "BEAUTY";
        }
        else if (type == TYPE_CUTE) {
            return "CUTE";
        }
        else if (type == TYPE_SEXY) {
            return "SEXY";
        }
        else {
            return "NULL";
        }

    }

    public void setType(int type) {
        this.type = type;
    }

    protected GirlFriend(Parcel in) {
        this.name = in.readString();
        this.type = in.readInt();
    }

    public static final Creator<GirlFriend> CREATOR = new Creator<GirlFriend>() {
        @Override
        public GirlFriend createFromParcel(Parcel in) {
            return new GirlFriend(in);
        }

        @Override
        public GirlFriend[] newArray(int size) {
            return new GirlFriend[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(type);
    }

    @Override
    public String toString() {
        return "GirlFriend{" + "name:" + this.name + ",type:" + getTypeDesc() + "}";
    }
}
