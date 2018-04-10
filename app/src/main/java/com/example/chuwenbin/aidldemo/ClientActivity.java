package com.example.chuwenbin.aidldemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.chuwenbin.aidldemo.bean.GirlFriend;

import java.util.Random;

/**
 * Created by chuwenbin on 2018/3/30.
 */

public class ClientActivity extends Activity {

    private Button mBtnMkGF;

    private final String TAG = getClass().getSimpleName();

    private IMyAidlInterface mAidlInterface;

    private String[] gfNames = {"lily", "marry", "randy"};
    private int[] gfTypes = {GirlFriend.TYPE_BEAUTY, GirlFriend.TYPE_CUTE, GirlFriend.TYPE_SEXY};

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            mAidlInterface = IMyAidlInterface.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAidlInterface = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        Intent intent = new Intent(ClientActivity.this, AIDLService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);

        mBtnMkGF = findViewById(R.id.btn_mk_gf);
        mBtnMkGF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int index = Math.abs(random.nextInt()) % 3;
                makeGF(gfNames[index], gfTypes[index]);
            }
        });
    }

    public void makeGF(String name, int type) {
        GirlFriend girlFriend = new GirlFriend(name, type);
        if (mAidlInterface == null) {
            return;
        }
        try {
            Log.d(TAG, "make gf:" + girlFriend.toString());
            mAidlInterface.makeGirlFriend(girlFriend);
            Log.d(TAG, "recent gf:" + mAidlInterface.getGirlFriend().toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
