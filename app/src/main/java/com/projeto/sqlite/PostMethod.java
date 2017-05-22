package com.projeto.sqlite;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Bruni on 18/05/2017.
 */

public class PostMethod {
    public static final  MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    String post(String url, String json) throws IOException
    {
        OkHttpClient client = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        builder .url(url);



        RequestBody body = RequestBody.create(JSON , json);
        builder.post(body);

        Request request = builder.build();

        Response response = client.newCall(request).execute();

        String resposta  = response.body().string();
        return resposta;
    }
}
