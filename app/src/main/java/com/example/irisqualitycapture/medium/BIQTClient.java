package com.example.irisqualitycapture.medium;

import android.content.Context;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class BIQTClient {

    public interface BIQTCallback {
        void onSuccess(JSONObject result);
        void onFailure(Exception e);
    }

    public static void sendImageFileToBIQTServer(Context context, File imageFile, String serverUrl, BIQTCallback callback) {
        OkHttpClient client = new OkHttpClient();

        RequestBody fileBody = RequestBody.create(imageFile, MediaType.parse("image/png"));
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imageFile.getName(), fileBody)
                .build();

        Request request = new Request.Builder()
                .url(serverUrl)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onFailure(new IOException("Unexpected code " + response));
                    return;
                }
                try {
                    String responseBody = response.body().string();
                    JSONObject json = new JSONObject(responseBody);
                    callback.onSuccess(json);
                } catch (Exception e) {
                    callback.onFailure(e);
                }
            }
        });
    }

}
