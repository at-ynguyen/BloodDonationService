package com.congybk.utlis;


import com.congybk.entity.User;
import com.google.gson.JsonObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @Author YNC on 07/04/2017.
 */
public final class PushNotificationUtils {
    private PushNotificationUtils() {
    }

    public static void pushFCMNotification(String userDeviceIdKey,String title,String body) throws Exception {

        String authKey = Constans.AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = Constans.API_URL_FCM;

        URL url = new URL(FMCurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authKey);
        conn.setRequestProperty("Content-Type", "application/json");
        JsonObject json = new JsonObject();
        json.addProperty("to", userDeviceIdKey.trim());
        JsonObject info = new JsonObject();
        info.addProperty("title", title); // Notification title
        info.addProperty("body", body); // Notification body
        json.add("notification", info);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(json.toString());
        wr.flush();
        conn.getInputStream();
    }

    public static void pushNotification(List<User> users, String title, String body) {
        for (User user : users) {
            try {
                if (user.getTokenPushNotification() != null) {
                    PushNotificationUtils.pushFCMNotification(user.getTokenPushNotification(), title, body);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
