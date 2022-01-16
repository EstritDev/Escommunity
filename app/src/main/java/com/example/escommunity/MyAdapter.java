package com.example.escommunity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private ArrayList<Posts> listaPosts;
        Context context;

        public MyAdapter(ArrayList<Posts> listaPosts){
                this.listaPosts = listaPosts;
        }

        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_posts, parent,false);
                return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

                String user = String.valueOf(listaPosts.get(position).getUser());
                String conteudo = String.valueOf(listaPosts.get(position).getConteudo());
                String dia = String.valueOf(listaPosts.get(position).getDia());
                holder.lblUser.setText(user);
                holder.lblConteudo.setText(conteudo);
                holder.lblHora.setText(dia);
                holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(context, EditarPerfilActivity.class);
                                intent.putExtra("conteudo", String.valueOf(listaPosts.get(holder.getAdapterPosition()).getConteudo()));
                                context.startActivity(intent);
                        }
                });
        }

        @Override
        public int getItemCount() {
                return listaPosts.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{
                LinearLayout mainLayout;
                TextView lblUser,lblConteudo,lblHora;
                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        lblUser = itemView.findViewById(R.id.lblUserPost);
                        lblConteudo = itemView.findViewById(R.id.lblConteudoPost);
                        lblHora = itemView.findViewById(R.id.lblHoraPost);
                        mainLayout = itemView.findViewById(R.id.mainLayout);
                }
        }
}
