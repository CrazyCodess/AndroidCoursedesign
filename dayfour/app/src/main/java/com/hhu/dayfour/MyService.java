package com.hhu.dayfour;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
    private MediaPlayer player;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    public void onStart(Intent intent,int startId){
        super.onStart(intent,startId);
        player = MediaPlayer.create(this,R.raw.notify);
        player.start();
    }
    public void onDestroy(){
        super.onDestroy();
        player.stop();
    }
}
