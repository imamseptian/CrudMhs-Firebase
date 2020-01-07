package com.example.crudmhs.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudmhs.MainActivity;
import com.example.crudmhs.R;
import com.example.crudmhs.model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Mahasiswa> {
    private Context nContext;
    int nResource;

    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Mahasiswa> objects) {
        super(context, resource, objects);
        this.nContext = context;
        nResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String nim = getItem(position).getNim();
        String nama = getItem(position).getNama();

//        Country country = new Country(name,capital);

        LayoutInflater inflater = LayoutInflater.from(nContext);
        convertView = inflater.inflate(nResource,parent,false);

        TextView tvNim = (TextView) convertView.findViewById(R.id.itemNIM);
        TextView tvNama = (TextView) convertView.findViewById(R.id.itemNama);

        tvNim.setText(nim);
        tvNama.setText(nama);

        return convertView;
    }
}
