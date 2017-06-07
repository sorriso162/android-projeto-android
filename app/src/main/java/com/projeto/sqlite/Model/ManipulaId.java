package com.projeto.sqlite.Model;

/**
 * Created by Bruni on 06/06/2017.
 */

public class ManipulaId {
    private static int id;

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    @Override
    public String toString() {
        return "ManipulaId{}";
    }
}
