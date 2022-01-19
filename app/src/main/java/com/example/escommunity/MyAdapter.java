package com.example.escommunity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

                String user = String.valueOf(listaPosts.get(position).getUser());
                String id = String.valueOf(listaPosts.get(position));
                String conteudo = String.valueOf(listaPosts.get(position).getConteudo());
                String dia = String.valueOf(listaPosts.get(position).getDia());
                holder.lblUser.setText(user);
                holder.lblConteudo.setText(conteudo);
                holder.lblHora.setText(dia);

                holder.imgEditar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent intent = new Intent(v.getContext(), EditPostActivity.class);
                                intent.putExtra("conteudo", conteudo);
                                intent.putExtra("id",id);
                                v.getContext().startActivity(intent);
                        }
                });
                holder.imgEliminar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                PostsDAO postsDAO = new PostsDAO(v.getContext());
                                Toast.makeText(v.getContext(), id, Toast.LENGTH_LONG).show();
                                postsDAO.eliminarPost(position);
                        }
                });
        }

        @Override
        public int getItemCount() {
                return listaPosts.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{
                TextView lblUser,lblConteudo,lblHora;
                ImageView imgEditar, imgEliminar;
                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        lblUser = itemView.findViewById(R.id.lblUser);
                        lblConteudo = itemView.findViewById(R.id.lblDescrição);
                        lblHora = itemView.findViewById(R.id.lblHoraPost);
                        imgEditar = itemView.findViewById(R.id.imgEditar);
                        imgEliminar = itemView.findViewById(R.id.imgEliminar);
                }
        }
}
