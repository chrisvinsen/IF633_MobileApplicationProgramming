package id.ac.umn.w10_28917.unbound_service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class CustomService extends Service {
    public CustomService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("CUSTOMSERVICE", "onCreate: CustomService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("CUSTOMSERVICE", "onStartCommand: " + startId);
        /*int n = (int) ((Math.random() * 50) + 10);
        try {
            for (int i = 0; i < n; i++) {
                Thread.sleep(200);
                Log.i("CUSTOMSERVICE", "onStartCommand: " + startId + " berjalan " + (int) ((100 * i) / (float) n) + " persen");
            }
            Log.i("CUSTOMSERVICE", "onStartCommand: " + startId + " selesai");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        AsyncTask customServiceTask = new CustomServiceTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, startId);

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("CUSTOMSERVICE", "onBind: Service Bind");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("CUSTOMSERVICE", "onDestroy: Service Destroyed");
    }
}