package com.shegi.fire_base_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shegi.fire_base_crud.Model.Request;

public class MainActivity extends AppCompatActivity {

    EditText edt_nama,edt_email,edt_pesan;
    Button btn_save,btn_cancel,btn_delete;
    private ProgressDialog loading;

    private DatabaseReference database;
    private String sPid,sPnama,sPemail,sPpesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_nama = findViewById(R.id.edt_nama);
        edt_email = findViewById(R.id.edi_email);
        edt_pesan = findViewById(R.id.edt_pesan);

        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);

        database = FirebaseDatabase.getInstance().getReference();

        sPid = getIntent().getStringExtra("id");
        sPnama = getIntent().getStringExtra("nama");
        sPemail = getIntent().getStringExtra("email");
        sPpesan = getIntent().getStringExtra("pesan");

        edt_nama.setText(sPnama);
        edt_email.setText(sPemail);
        edt_pesan.setText(sPpesan);

        if (sPid.equals("")){
            btn_save.setText("Save");
           btn_cancel.setText("Cancel");
        }else {
            btn_save.setText("Edit");
           btn_cancel.setText("Delete");

        }



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = edt_nama.getText().toString().trim();
                String email = edt_email.getText().toString().trim();
                String pesan = edt_pesan.getText().toString().trim();

                if (btn_save.getText().equals("Save")){
                    // Perintah Menyimpan
                    if (nama.equals("")){
                        edt_nama.setError("Silahkan Masukan nama");
                        edt_nama.requestFocus();
                    }else if (email.equals("")){
                        edt_email.setError("Silahkan Masukan email");
                        edt_email.requestFocus();
                    }else if (pesan.equals("")){
                        edt_pesan.setError("Silahkan Masukan pesan");
                        edt_pesan.requestFocus();
                    }else {
                        loading = ProgressDialog.show(MainActivity.this,
                                null,
                                "Mohon Tunggu ....",
                                true,
                                false);

                        Kirim(new Request(nama.toLowerCase(),
                                email.toLowerCase(),
                                pesan.toLowerCase()));

                    }
                }else {
                    // Perintah Mengedit
                    if (nama.equals("")){
                        edt_nama.setError("Silahkan Masukan nama");
                        edt_nama.requestFocus();
                    }else if (email.equals("")){
                        edt_email.setError("Silahkan Masukan email");
                        edt_email.requestFocus();
                    }else if (pesan.equals("")){
                        edt_pesan.setError("Silahkan Masukan pesan");
                        edt_pesan.requestFocus();
                    }else{
                        loading = ProgressDialog.show(MainActivity.this,
                                null,
                                "Mohon Tunggu ....",
                                true,
                                false);
                        Edituser(new Request(
                                nama.toLowerCase(),
                                email.toLowerCase(),
                                pesan.toLowerCase()),sPid);
                    }
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_cancel.getText().equals("Cancel")){
                    finish();
                }else{
                    // Perintah menghapus data
                    database.child("Agus kurniawan")
                            .child(sPid)
                            .removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(MainActivity.this, "Data Berhasil Di Hapus",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

    }

    private void Kirim(Request request){
        database.child("Agus kurniawan")
                .push()
                .setValue(request)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        loading.dismiss();

                        edt_nama.setText("");
                        edt_email.setText("");
                        edt_pesan.setText("");

                        Toast.makeText(MainActivity.this, "Data berhasil di simpan", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void Edituser(Request request,String id){
        database.child("Agus kurniawan")
                .child(id)
                .setValue(request)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        loading.dismiss();

                        edt_nama.setText("");
                        edt_email.setText("");
                        edt_pesan.setText("");

                        Toast.makeText(MainActivity.this, "Data Berhasil Di edit", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
