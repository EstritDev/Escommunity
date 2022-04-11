package com.example.MyUniverse.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyUniverse.R;
import com.example.MyUniverse.constructors.Utilizador;
import com.example.MyUniverse.fragmentos.Perfil;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    ArrayList<Utilizador> listaUtilizadores;
    String loginId;
    Context context;


    public UsersAdapter(ArrayList<Utilizador> listaUtilizadores, String loginId){
        this.listaUtilizadores = listaUtilizadores;
        this.loginId = loginId;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_users, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String user = String.valueOf(listaUtilizadores.get(position).getNome());
        String userProfileId = String.valueOf(listaUtilizadores.get(position).getLoginId());
        String desc = String.valueOf(listaUtilizadores.get(position).getDesc());

        holder.lblUser.setText(user);
        holder.lblLoginId.setText(userProfileId);
        holder.lblDesc.setText(desc);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Perfil perfil = new Perfil();
                Bundle bundle = new Bundle();
                bundle.putString("loginId", loginId);
                bundle.putString("userProfileId", userProfileId);
                perfil.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, perfil).commit();
            }
        });


    }


    @Override
    public int getItemCount() {
        return listaUtilizadores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView lblUser, lblLoginId, lblDesc;
        LinearLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblUser = itemView.findViewById(R.id.lblUserFollows);
            lblLoginId = itemView.findViewById(R.id.lblLoginIdFollows);
            lblDesc = itemView.findViewById(R.id.lblDescProc);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
