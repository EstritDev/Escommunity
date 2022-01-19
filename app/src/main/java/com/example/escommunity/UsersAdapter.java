package com.example.escommunity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private ArrayList<Utilizador> listaUtilizadores;
    Context context;

    public UsersAdapter(ArrayList<Utilizador> listaUtilizadores){
        this.listaUtilizadores = UsersAdapter.this.listaUtilizadores;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_user, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String user = String.valueOf(listaUtilizadores.get(position).getNome());
        String loginId = String.valueOf(listaUtilizadores.get(position).getLoginId());
        String desc = String.valueOf(listaUtilizadores.get(position).getDesc());

        holder.lblUser.setText(user);
        holder.lblLoginId.setText(loginId);
        holder.lblDesc.setText(desc);
    }


    @Override
    public int getItemCount() {
        return listaUtilizadores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView lblUser, lblLoginId, lblDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblUser = itemView.findViewById(R.id.lblUser);
            lblLoginId = itemView.findViewById(R.id.lblLoginId);
            lblDesc = itemView.findViewById(R.id.lblDesc);
        }
    }
}
