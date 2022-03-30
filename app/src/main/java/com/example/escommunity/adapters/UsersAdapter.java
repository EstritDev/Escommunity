package com.example.escommunity.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.escommunity.R;
import com.example.escommunity.activities.PerfilActivity;
import com.example.escommunity.constructors.Utilizador;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    ArrayList<Utilizador> listaUtilizadores;
    Context context;


    public UsersAdapter(ArrayList<Utilizador> listaUtilizadores){
        this.listaUtilizadores = listaUtilizadores;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_user, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String user = String.valueOf(listaUtilizadores.get(position).getNome());
        String loginId = String.valueOf(listaUtilizadores.get(position).getLoginId());
        String desc = String.valueOf(listaUtilizadores.get(position).getDesc());

        holder.lblUser.setText(user);
        holder.lblLoginId.setText(loginId);
        holder.lblDesc.setText(desc);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PerfilActivity.class);
                intent.putExtra("userProfileLoginId", String.valueOf(listaUtilizadores.get(position).getLoginId()));
                intent.putExtra("loginId", setUserLoginId(loginId));
                view.getContext().startActivity(intent);
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

            lblUser = itemView.findViewById(R.id.lblUserProc);
            lblLoginId = itemView.findViewById(R.id.lblLoginIdProc);
            lblDesc = itemView.findViewById(R.id.lblDescProc);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }

    public String setUserLoginId(String loginUserId){
        String loginUserid = loginUserId;
        return loginUserid;
    }
}
