package com.voicemeeter.remote;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private VoicemeeterController controller;
    private EditText ipAddress;
    private Button connectBtn;
    private SeekBar[] stripSliders = new SeekBar[8];
    private Button[] stripMuteButtons = new Button[8];
    private SeekBar[] busSliders = new SeekBar[8];
    private Button[] busMuteButtons = new Button[8];
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        controller = new VoicemeeterController();
        initViews();
        setupListeners();
    }

    private void initViews() {
        ipAddress = findViewById(R.id.ip_address);
        connectBtn = findViewById(R.id.connect_btn);
        statusText = findViewById(R.id.status_text);
        
        // Inizializza strip (input)
        for (int i = 0; i < 8; i++) {
            int sliderId = getResources().getIdentifier("strip_volume_" + i, "id", getPackageName());
            int buttonId = getResources().getIdentifier("strip_mute_" + i, "id", getPackageName());
            
            stripSliders[i] = findViewById(sliderId);
            stripMuteButtons[i] = findViewById(buttonId);
            
            final int channel = i;
            
            if (stripSliders[i] != null) {
                stripSliders[i].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            float volume = progress / 100.0f;
                            controller.setStripVolume(channel, volume);
                        }
                    }
                    @Override public void onStartTrackingTouch(SeekBar seekBar) {}
                    @Override public void onStopTrackingTouch(SeekBar seekBar) {}
                });
            }
            
            if (stripMuteButtons[i] != null) {
                stripMuteButtons[i].setOnClickListener(v -> controller.toggleStripMute(channel));
            }
        }
        
        // Inizializza bus (output)
        for (int i = 0; i < 8; i++) {
            int sliderId = getResources().getIdentifier("bus_volume_" + i, "id", getPackageName());
            int buttonId = getResources().getIdentifier("bus_mute_" + i, "id", getPackageName());
            
            busSliders[i] = findViewById(sliderId);
            busMuteButtons[i] = findViewById(buttonId);
            
            final int channel = i;
            
            if (busSliders[i] != null) {
                busSliders[i].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            float volume = progress / 100.0f;
                            controller.setBusVolume(channel, volume);
                        }
                    }
                    @Override public void onStartTrackingTouch(SeekBar seekBar) {}
                    @Override public void onStopTrackingTouch(SeekBar seekBar) {}
                });
            }
            
            if (busMuteButtons[i] != null) {
                busMuteButtons[i].setOnClickListener(v -> controller.toggleBusMute(channel));
            }
        }
    }

    private void setupListeners() {
        connectBtn.setOnClickListener(v -> {
            String ip = ipAddress.getText().toString();
            if (controller.connect(ip)) {
                statusText.setText("Connesso a " + ip);
                connectBtn.setText("Disconnetti");
            } else {
                statusText.setText("Errore connessione");
            }
        });
    }
}