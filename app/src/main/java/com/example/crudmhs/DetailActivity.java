package com.example.crudmhs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    private TextView tvnim,tvnama,tvipk,tvsemester;
    private String vnim,vnama,vkey;
    private int vsemester;
    private float vipk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);

        tvnim = (TextView)findViewById(R.id.tvNIM);
        tvnama = (TextView)findViewById(R.id.tvNama);
        tvipk = (TextView)findViewById(R.id.tvIpk);
        tvsemester = (TextView)findViewById(R.id.tvSemester);

        vnim = getIntent().getStringExtra("nim");
        vnama = getIntent().getStringExtra("nama");
        vipk = getIntent().getFloatExtra("ipk",0);
        vsemester = getIntent().getIntExtra("semester",0);
        vkey = getIntent().getStringExtra("id");
        Log.d(TAG, "onCreate: "+Integer.toString(vsemester));


        tvnim.setText(vnim);
        tvnama.setText(vnama);
        tvipk.setText(Float.toString(vipk));
        tvsemester.setText(Integer.toString(vsemester));
    }


    public void goBack(View view) {
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }

    public void goEdit(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("id",vkey);
        intent.putExtra("nama",vnama);
        intent.putExtra("nim",vnim);
        intent.putExtra("semester",vsemester);
        intent.putExtra("ipk",vipk);
        startActivity(intent);
    }
}
