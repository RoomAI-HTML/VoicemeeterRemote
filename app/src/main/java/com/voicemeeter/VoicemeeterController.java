package com.voicemeeter.remote;

import okhttp3.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class VoicemeeterController {
    private OkHttpClient client;
    private String baseUrl;
    private boolean connected = false;

    public VoicemeeterController() {
        client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();
    }

    public boolean connect(String ipAddress) {
        baseUrl = "http://" + ipAddress + ":8080"; // Porta Voicemeeter Potato web interface
        
        // Test connessione
        Request request = new Request.Builder()
            .url(baseUrl + "/api/status")
            .build();
            
        try {
            Response response = client.newCall(request).execute();
            connected = response.isSuccessful();
            response.close();
            return connected;
        } catch (IOException e) {
            connected = false;
            return false;
        }
    }

    // Controlli Strip (Input)
    public void setStripVolume(int channel, float volume) {
        if (!connected) return;
        sendVolumeCommand("/api/strip/" + channel + "/gain", volume);
    }

    public void toggleStripMute(int channel) {
        if (!connected) return;
        sendMuteCommand("/api/strip/" + channel + "/mute/toggle");
    }

    // Controlli Bus (Output)
    public void setBusVolume(int channel, float volume) {
        if (!connected) return;
        sendVolumeCommand("/api/bus/" + channel + "/gain", volume);
    }

    public void toggleBusMute(int channel) {
        if (!connected) return;
        sendMuteCommand("/api/bus/" + channel + "/mute/toggle");
    }

    private void sendVolumeCommand(String endpoint, float volume) {
        float volumeDb = volume == 0 ? -60 : (float)(20 * Math.log10(volume));
        
        RequestBody body = RequestBody.create(
            String.valueOf(volumeDb), 
            MediaType.parse("text/plain")
        );
        
        Request request = new Request.Builder()
            .url(baseUrl + endpoint)
            .post(body)
            .build();
            
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                response.close();
            }
        });
    }

    private void sendMuteCommand(String endpoint) {
        Request request = new Request.Builder()
            .url(baseUrl + endpoint)
            .post(RequestBody.create("", MediaType.parse("text/plain")))
            .build();
            
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                response.close();
            }
        });
    }

    public void disconnect() {
        connected = false;
    }
}