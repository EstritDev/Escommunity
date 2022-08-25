package com.example.MyUniverse.adapters;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyUniverse.R;
import com.example.MyUniverse.activities.EditPostActivity;
import com.example.MyUniverse.constructors.Posts;
import com.example.MyUniverse.daos.LikesDAO;
import com.example.MyUniverse.daos.PostsDAO;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

        private ArrayList<Posts> listaPosts;
        Context context;
        LikesDAO likesDAO;

        public PostsAdapter(ArrayList<Posts> listaPosts){
                this.listaPosts = listaPosts;
        }

        @NonNull
        @Override
        public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_posts, parent,false);
                likesDAO = new LikesDAO(view.getContext());
                return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
                String idPost = String.valueOf(listaPosts.get(position).getIdPost());
                String user = String.valueOf(listaPosts.get(position).getUser());
                String conteudo = String.valueOf(listaPosts.get(position).getConteudo());
                String dia = String.valueOf(listaPosts.get(position).getDia());
                int likes = likesDAO.getLikes(Integer.parseInt(idPost));

                holder.lblUser.setText(user);
                holder.lblConteudo.setText(conteudo);
                holder.lblHora.setText(dia);
                holder.lblLikes.setText(likes);

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

                holder.imgLike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                //if(holder.imgLike.source)
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
                TextView lblUser,lblConteudo,lblHora, lblLikes;
                ImageView imgEditar, imgEliminar, imgLike;
                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        lblUser = itemView.findViewById(R.id.lblUserPost);
                        lblConteudo = itemView.findViewById(R.id.lblConteudo);
                        lblHora = itemView.findViewById(R.id.lblHoraPost);
                        lblLikes = itemView.findViewById(R.id.lblLikes);
                        imgEditar = itemView.findViewById(R.id.imgEditar);
                        imgEliminar = itemView.findViewById(R.id.imgEliminar);
                        imgLike = itemView.findViewById(R.id.imgLikes);
                }
        }
}
