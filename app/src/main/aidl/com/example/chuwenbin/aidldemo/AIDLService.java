package com.example.chuwenbin.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.chuwenbin.aidldemo.bean.GirlFriend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chuwenbin on 2018/3/30.
 */

public class AIDLService extends Service {

    public final String TAG = getClass().getSimpleName();

    private List<GirlFriend> mGFs;

    private IBinder mIBinder = new IMyAidlInterface.Stub() {

        @Override
        public void makeGirlFriend(GirlFriend gf) throws RemoteException {
            Log.d(TAG, "make girl friend:" + gf);
            mGFs.add(gf);
        }

        @Override
        public GirlFriend getGirlFriend() throws RemoteException {
            return mGFs.get(mGFs.size() - 1);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mGFs = new ArrayList<>();
        Log.d(TAG, "onBind");
        return mIBinder;
    }
}
