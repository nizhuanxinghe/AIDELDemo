// IMyAidlInterface.aidl
package com.example.chuwenbin.aidldemo;

// Declare any non-default types here with import statements
import com.example.chuwenbin.aidldemo.bean.GirlFriend;

interface IMyAidlInterface {

    void makeGirlFriend(in GirlFriend gf);

    GirlFriend getGirlFriend();
}
