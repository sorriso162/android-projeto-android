package com.projeto.sqlite.Model;

import com.google.gson.Gson;

/**
 * Created by Andre on 28/05/2017.
 */

public class ConvertGson {
    public String converteParaJson(Object ld) {
        Gson gson = new Gson();
        String json = gson.toJson(ld);
        return json;
    }
}
