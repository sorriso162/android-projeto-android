package com.projeto.sqlite.Network;

import com.google.gson.Gson;

/**
 * Created by Andre on 28/05/2017.
 */

public class ConvertGson {
    Gson gson = new Gson();

    /*
        Convertendo o objeto em JSON
     */
    public String converteParaJson(Object ld) {
        String json = gson.toJson(ld);
        return json;
    }
}
