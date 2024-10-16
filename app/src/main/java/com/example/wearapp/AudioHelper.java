package com.example.wearapp;

import android.os.Bundle;


import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.content.pm.PackageManager;

//extends AppCompatActivity
public class AudioHelper {

    private AudioManager audioManager;
    private Context context;

    public AudioHelper(Context context) {
        this.context = context;
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public boolean audioOutputAvailable(int type) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_AUDIO_OUTPUT)) {
            return false;
        }

        AudioDeviceInfo[] devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);
        for (AudioDeviceInfo device : devices) {
            if (device.getType() == type) {
                return true;
            }
        }
        return false;
    }
}

// Exemplo de uso
// Esse exemplo deve ser executado dentro de uma Activity ou Service onde o contexto está disponível

// Dentro de uma Activity
/*
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioHelper audioHelper = new AudioHelper(this);

        boolean isSpeakerAvailable = audioHelper.audioOutputAvailable(AudioDeviceInfo.TYPE_BUILTIN_SPEAKER);
        System.out.println("Speaker available: " + isSpeakerAvailable);

        boolean isBluetoothHeadsetConnected = audioHelper.audioOutputAvailable(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP);
        System.out.println("Bluetooth headset connected: " + isBluetoothHeadsetConnected);
    }
}
*/