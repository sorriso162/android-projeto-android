package com.projeto.sqlite;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projeto.sqlite.Model.ChamadoDados;

import java.util.ArrayList;

/**
 * Created by Leonan-Mac on 9/23/16.
 */
public class CustomAdapter extends ArrayAdapter<ChamadoDados> {

    private Context context;

    public CustomAdapter(Context context, int resource, ArrayList<ChamadoDados> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        ChamadoDados p = getItem(position);

        if (converView == null) {
            converView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        TextView txtName = (TextView) converView.findViewById(R.id.txt_label);
        TextView txtId = (TextView) converView.findViewById(R.id.txt_label_id);

        txtName.setText(p.getDescricao());
        txtId.setText(String.valueOf(p.getId()));

        return converView;
    }

}