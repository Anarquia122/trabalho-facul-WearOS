package com.example.wearapp;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class MyNotificationListenerService extends NotificationListenerService {

    private TextToSpeech textToSpeech;

    @Override
    public void onCreate() {
        super.onCreate();
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.getDefault());
            } else {
                Log.e("TTS", "Initialization failed");
            }
        });
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String notificationText = sbn.getNotification().extras.getString("android.text").toString();
        speak(notificationText);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {}

    private void speak(String text) {
        if (textToSpeech != null && textToSpeech.getEngines().size() != 0) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            Log.e("TTS", "TextToSpeech not initialized or no engines available");
        }
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
