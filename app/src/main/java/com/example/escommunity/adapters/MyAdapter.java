package com.example.escommunity.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.escommunity.R;
import com.example.escommunity.activities.EditPostActivity;
import com.example.escommunity.constructors.Posts;
import com.example.escommunity.daos.PostsDAO;

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
                String idPost = String.valueOf(listaPosts.get(position).getIdPost());
                String user = String.valueOf(listaPosts.get(position).getUser());
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
                                intent.putExtra("idPost", idPost);
                                v.getContext().startActivity(intent);
                        }
                });
                holder.imgEliminar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                AlertDialog.Builder caixa = new AlertDialog.Builder(v.getContext());
                                caixa.setMessage("Are you sure you wanna delete this post?");
                                caixa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                                PostsDAO postsDAO = new PostsDAO(v.getContext());
                                                postsDAO.eliminarPost(Integer.parseInt(idPost));
                                                updateItems(listaPosts,v);
                                        }
                                });
                                caixa.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                        }
                                });
                                AlertDialog alertDialog = caixa.create();
                                alertDialog.show();
                        }
                });
        }

        @Override
        public int getItemCount() {
                return listaPosts.size();
        }

        public void updateItems(ArrayList<Posts> novaListaPosts, View v) {
                PostsDAO postsDAO = new PostsDAO(v.getContext());
                listaPosts.clear();
                listaPosts = postsDAO.getPosts();
                listaPosts.addAll(novaListaPosts);
                this.notifyDataSetChanged();
        }


        public static class ViewHolder extends RecyclerView.ViewHolder{
                TextView lblUser,lblConteudo,lblHora;
                ImageView imgEditar, imgEliminar;
                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        lblUser = itemView.findViewById(R.id.lblUserPost);
                        lblConteudo = itemView.findViewById(R.id.lblConteudo);
                        lblHora = itemView.findViewById(R.id.lblHoraPost);
                        imgEditar = itemView.findViewById(R.id.imgEditar);
                        imgEliminar = itemView.findViewById(R.id.imgEliminar);
                }
        }
}
