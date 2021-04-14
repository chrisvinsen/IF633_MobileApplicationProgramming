package id.ac.umn.w10_28917.unbound_service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class SimpleIntentService extends IntentService {

    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("INTENTSERVICE", "onHandleIntent: IntentService dimulai !");
        int n = (int) ((Math.random() * 50) + 10);
        try {
            for (int i = 0; i < n; i++) {
                Thread.sleep(200);
                Log.i("INTENTSERVICE", "onHandleIntent: IntentService berjalan " + (int) ((100 * i) / (float) n) + " persen");
            }
            Log.i("INTENTSERVICE", "onHandleIntent: IntentService selesai !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
