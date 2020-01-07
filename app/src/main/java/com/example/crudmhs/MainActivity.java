package com.example.crudmhs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudmhs.model.Mahasiswa;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DatabaseReference database;

    private EditText etnim,etnama,etipk,etsemester;
    private ProgressDialog loading;
    private Button btsubmit,btcancel;
    private String sId,sNim,sNama;
    private int sSemester;
    private float sIPK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_add);

        database = FirebaseDatabase.getInstance().getReference();

        sId=getIntent().getStringExtra("id");
        sNim=getIntent().getStringExtra("nim");
        sNama=getIntent().getStringExtra("nama");
        sSemester=getIntent().getIntExtra("semester",0);
        sIPK=getIntent().getFloatExtra("ipk",0);

        etnim=(EditText)findViewById(R.id.etNIM);
        etnama=(EditText)findViewById(R.id.etNama);
        etipk=(EditText)findViewById(R.id.etIPK);
        etsemester=(EditText)findViewById(R.id.etSemester);

        etnim.setText(sNim);
        etnama.setText(sNama);


        btsubmit=(Button)findViewById(R.id.btSubmit);
        btcancel=(Button)findViewById(R.id.btCancel);

        if(sId.equals("")){
            btsubmit.setText("Save");
            btcancel.setText("Cancel");
            etipk.setText("");
            etsemester.setText("");
        }else{
            btsubmit.setText("Edit");
            btcancel.setText("Go Back");
            etipk.setText(Float.toString(sIPK));
            etsemester.setText(Integer.toString(sSemester));
        }

        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String snim = etnim.getText().toString();
                String snama = etnama.getText().toString();
                String sipk = etipk.getText().toString();
                String ssemester = etsemester.getText().toString();
                int uSemester=0;
                float uIpk=0;
                if(btsubmit.getText().equals("Save")){
                    if(snim.equals("")){
                        etnim.setError("Silahkan masukan NIM");
                        etnim.requestFocus();
                    }else if(snama.equals("")){
                        etnama.setError("Silahkan masukan Nama");
                        etnama.requestFocus();
                    }else if(ssemester.equals("")){
                        etsemester.setError("Silahkan masukan Semester");
                        etsemester.requestFocus();
                    }else if(sipk.equals("")){
                        etipk.setError("Silahkan masukan IPK");
                        etipk.requestFocus();
                    }else {
                        uSemester = Integer.parseInt(ssemester);
                        uIpk = Float.parseFloat(sipk);
                        loading = ProgressDialog.show(MainActivity.this,null,"Please wait ....",true,false);
                        submitMhs(new Mahasiswa(snim,snama,uSemester,uIpk));
                    }
                }else{
                    if(snim.equals("")){
                        etnim.setError("Silahkan masukan NIM");
                        etnim.requestFocus();
                    }else if(snama.equals("")){
                        etnama.setError("Silahkan masukan Nama");
                        etnama.requestFocus();
                    }else if(ssemester.equals("")){
                        etsemester.setError("Silahkan masukan Semester");
                        etsemester.requestFocus();
                    }else if(sipk.equals("")){
                        etipk.setError("Silahkan masukan IPK");
                        etipk.requestFocus();
                    }else {
                        uSemester = Integer.parseInt(ssemester);
                        uIpk = Float.parseFloat(sipk);
                        loading = ProgressDialog.show(MainActivity.this,null,"Please wait ....",true,false);
                        EditMhs(new Mahasiswa(snim,snama,uSemester,uIpk),sId);
                    }
                }

            }
        });
        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void submitMhs(Mahasiswa req){
        Log.d(TAG, "submitMhs: ");
        database.child("Mahasiswa").push().setValue(req).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                loading.dismiss();
                etnim.setText("");
                etnama.setText("");
                etipk.setText("");
                etsemester.setText("");

                Toast.makeText(MainActivity.this,"Data berhasil di add",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void EditMhs(Mahasiswa req,String id){
        Log.d(TAG, "EditMhs: ");
        database.child("Mahasiswa").child(id).setValue(req).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                loading.dismiss();
                etnim.setText("");
                etnama.setText("");
                etipk.setText("");
                etsemester.setText("");

                Toast.makeText(MainActivity.this,"Data berhasil di add",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
