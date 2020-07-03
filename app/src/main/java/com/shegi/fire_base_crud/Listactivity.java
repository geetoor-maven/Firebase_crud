package com.shegi.fire_base_crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shegi.fire_base_crud.Adapter.Requestadapterrcl;
import com.shegi.fire_base_crud.Model.Request;

import java.util.ArrayList;

public class Listactivity extends AppCompatActivity {

    private DatabaseReference database;
    private ArrayList<Request> daftarreq;
    private Requestadapterrcl requestadapterrcl;

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);

        database = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.rcl_list);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        progressDialog = ProgressDialog.show(Listactivity.this,
                null,
                "Mohon Tunggu ...",
                true,
                false);

        findViewById(R.id.flt_tambah).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Listactivity.this,MainActivity.class);
                intent.putExtra("id","");
                intent.putExtra("nama","");
                intent.putExtra("email","");
                intent.putExtra("pesan","");
                startActivity(intent);
            }
        });

        database.child("Agus kurniawan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                daftarreq = new ArrayList<>();

                for (DataSnapshot notedatasnapshot : dataSnapshot.getChildren()){

                    Request request = notedatasnapshot.getValue(Request.class);
                    request.setKey(notedatasnapshot.getKey());

                    daftarreq.add(request);
                }

                requestadapterrcl = new Requestadapterrcl(daftarreq,Listactivity.this);
                recyclerView.setAdapter(requestadapterrcl);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                progressDialog.dismiss();
            }
        });


    }
}
