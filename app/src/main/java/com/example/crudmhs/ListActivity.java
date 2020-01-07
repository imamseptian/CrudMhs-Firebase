package com.example.crudmhs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudmhs.adapter.MyAdapter;
import com.example.crudmhs.model.Mahasiswa;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "ListActivity";

    private DatabaseReference dataMahasiswa;
    private ArrayList<Mahasiswa> daftarMhs;
    private MyAdapter myAdapter;

    private ListView listView;
    private ProgressDialog loading;
    LayoutInflater inflater1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_mhs);

        dataMahasiswa = FirebaseDatabase.getInstance().getReference();
        listView = (ListView) findViewById(R.id.lvMhs);


        loading = ProgressDialog.show(ListActivity.this,null,"Please Wait",true,false);
        dataMahasiswa.child("Mahasiswa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                daftarMhs = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Mahasiswa mhs = noteDataSnapshot.getValue(Mahasiswa.class);
                    mhs.setKey(noteDataSnapshot.getKey());

                    daftarMhs.add(mhs);
                }

                myAdapter = new MyAdapter(ListActivity.this,R.layout.item_mhs,daftarMhs);
                listView.setAdapter(myAdapter);
                loading.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                loading.dismiss();
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        switch (item.getItemId()){
            case R.id.itemDetail:{
                Intent intent = new Intent(this,DetailActivity.class);
                Mahasiswa selec = daftarMhs.get(index);
                intent.putExtra("id",selec.getKey());
                intent.putExtra("nama",selec.getNama());
                intent.putExtra("nim",selec.getNim());
                intent.putExtra("semester",selec.getSemester());
                intent.putExtra("ipk",selec.getIpk());
                startActivity(intent);
                break;
            }
            case R.id.itemUpdate:{
                Intent intent = new Intent(this,MainActivity.class);
                Mahasiswa selec = daftarMhs.get(index);
                intent.putExtra("id",selec.getKey());
                intent.putExtra("nama",selec.getNama());
                intent.putExtra("nim",selec.getNim());
                intent.putExtra("semester",selec.getSemester());
                intent.putExtra("ipk",selec.getIpk());
                startActivity(intent);
                break;
            }
            case R.id.itemDelete:{
                dataMahasiswa.child("Mahasiswa").child(daftarMhs.get(index).getKey()).removeValue().addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        loading.dismiss();
                        Toast.makeText(ListActivity.this,"Data berhasil di hapus",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

        }

        return super.onContextItemSelected(item);
    }


    public void addMhs(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("id","");
        intent.putExtra("nama","");
        intent.putExtra("nim","");
        intent.putExtra("semester",0);
        intent.putExtra("ipk",0);
        startActivity(intent);
    }
}
