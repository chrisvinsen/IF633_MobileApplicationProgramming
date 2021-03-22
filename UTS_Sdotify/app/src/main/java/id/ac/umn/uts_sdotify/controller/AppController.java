package id.ac.umn.uts_sdotify.controller;

import android.app.Activity;
import android.content.Intent;

import id.ac.umn.uts_sdotify.ui.activity.AboutActivity;
import id.ac.umn.uts_sdotify.ui.activity.LoginActivity;
import id.ac.umn.uts_sdotify.ui.activity.MainActivity;
import id.ac.umn.uts_sdotify.ui.activity.WelcomeActivity;

public class AppController {

    public static void openWelcomeActivity(final Activity activity) {
        final Intent intent = new Intent(activity, WelcomeActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void openLoginActivity(final Activity activity, boolean finish) {
        final Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        if (finish) {
            activity.finish();
        }
    }

    public static void openAboutActivity(final Activity activity) {
        final Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }

    public static void openMainActivity(final Activity activity, boolean alert) {
        Intent intent = new Intent(activity, MainActivity.class);
        if (alert) {
            intent.putExtra("AlertStatus", "true");
        }
        activity.startActivity(intent);
        activity.finish();
    }
}
