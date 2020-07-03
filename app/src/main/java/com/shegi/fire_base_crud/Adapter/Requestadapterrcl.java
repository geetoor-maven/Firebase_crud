package com.shegi.fire_base_crud.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shegi.fire_base_crud.MainActivity;
import com.shegi.fire_base_crud.Model.Request;
import com.shegi.fire_base_crud.R;

import java.util.List;

/**
 * created by shegi-developer on 01/07/20
 */
public class Requestadapterrcl extends RecyclerView.Adapter<Requestadapterrcl.MyviewHolder> {

    private List<Request> movielist;
    private Activity mActivity;

    public class MyviewHolder extends RecyclerView.ViewHolder{

        public LinearLayout linearLayout;
        public TextView txt_nama,txt_email,txt_pesan;

        public MyviewHolder(View view){
            super(view);

            linearLayout = view.findViewById(R.id.linear_item);
            txt_nama = view.findViewById(R.id.txt_nama);
            txt_email = view.findViewById(R.id.txt_email);
            txt_pesan = view.findViewById(R.id.txt_pesan);

        }
    }

    public Requestadapterrcl(List<Request> movielist,Activity mActivity){
        this.movielist = movielist;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request,parent,false);
        return new MyviewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        final Request movie = movielist.get(position);

        holder.txt_nama.setText(movie.getNama());
        holder.txt_email.setText(movie.getEmail());
        holder.txt_pesan.setText(movie.getPesan());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mActivity, MainActivity.class);
                intent.putExtra("id",movie.getKey());
                intent.putExtra("nama",movie.getNama());
                intent.putExtra("email",movie.getEmail());
                intent.putExtra("pesan",movie.getPesan());
                mActivity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movielist.size();
    }
}
