package com.theta360.pluginapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.theta360.pluginlibrary.activity.PluginActivity;
import com.theta360.pluginlibrary.callback.KeyCallback;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends PluginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setKeyCallback(new KeyCallback() {

            @Override
            public void onKeyDown(int keyCode, KeyEvent event) {
                new SendMessageTask().execute();
            }

            public void onKeyUp(int keyCode, KeyEvent event) {
            }

            public void onKeyLongPress(int keyCode, KeyEvent event) {
            }
        });
    }

    private static class SendMessageTask extends AsyncTask<Void, Void, Void> {

        @Override
        public Void doInBackground(Void... params) {
            final String token = "<Access Token>";
            final String user_id = "<User ID>";

            final String data = "{"
                    + "\"to\":\""
                    + user_id
                    + "\","
                    + "\"messages\":["
                    + "{\"type\":\"text\",\"text\":\"I love you\"},"
                    + "{\"type\":\"text\",\"text\":\"I love you so much\"},"
                    + "{\"type\":\"sticker\",\"packageId\":\"1\",\"stickerId\":\"4\"}"
                    + "]}";
            try {
                final String url = "https://api2.line.me/v2/bot/message/push";
                HttpURLConnection connection = createConnection(url, token);
                sendData(data, connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private HttpURLConnection createConnection(String url, String token) throws IOException {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            // Settings for connecting
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Accept", "application/json");

            // Allows settings for return value from API and data to be sent
            connection.setDoOutput(true);

            return connection;
        }

        private void sendData(String data, HttpURLConnection connection) throws IOException {
            try (OutputStream out = connection.getOutputStream()) {
                // Connecting
                connection.connect();

                // Write data to be sent
                out.write(data.getBytes("UTF-8"));
                out.flush();
                connection.getResponseCode();
            } finally {
                connection.disconnect();
            }
        }
    }
}