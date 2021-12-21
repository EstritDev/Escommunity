package com.example.escommunity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        holder.lblNome.setText(nome);
        holder.lblIdade.setText(idade);
        holder.lblEquipa.setText(equipa);
        holder.lblCamisola.setText(nCamisola);
    }

    @Override
    public int getItemCount() {
        return listaJogadores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblNome,lblIdade,lblEquipa,lblCamisola;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblNome = itemView.findViewById(R.id.lblNome);
            lblIdade = itemView.findViewById(R.id.lblIdade);
            lblEquipa = itemView.findViewById(R.id.lblEquipa);
            lblCamisola = itemView.findViewById(R.id.lblNumeroCamisola);
        }
    }
}