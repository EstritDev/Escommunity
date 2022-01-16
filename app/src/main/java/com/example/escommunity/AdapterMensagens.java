package com.example.escommunity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMensagens extends RecyclerView.Adapter<AdapterMensagens.ViewHolder> {

    private ArrayList<Mensagens> listaMensagens;
    Context context;

    public AdapterMensagens(ArrayList<Mensagens> listaMensagens){
        this.listaMensagens = listaMensagens;
    }

    @NonNull
    @Override
    public AdapterMensagens.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_mensagem, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String nome = String.valueOf(listaMensagens.get(position).getUtilizador());
        String hora = String.valueOf(listaMensagens.get(position).getHora());
        String mensagem = String.valueOf(listaMensagens.get(position).getMensagem());
        holder.lblUserPost.setText(nome);
        holder.lblConteudoPost.setText(mensagem);
        holder.lblHoraPost.setText(hora);
    }

    @Override
    public int getItemCount() {
        return listaMensagens.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblUserPost,lblConteudoPost,lblHoraPost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblUserPost = itemView.findViewById(R.id.lblUserPost);
            lblConteudoPost = itemView.findViewById(R.id.lblConteudoPost);
            lblHoraPost = itemView.findViewById(R.id.lblHoraPost);
        }
    }
}