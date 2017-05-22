package com.projeto.sqlite.Request;

import android.util.Log;
import android.widget.Toast;

import com.projeto.sqlite.MainActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Andre on 21/05/2017.
 */

public class ChamadaRequest {

    private final OkHttpClient client = new OkHttpClient();

    public void getRequest(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, IOException e) {
                e.getMessage();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                response.body().string();
            }
        });
    }
}
