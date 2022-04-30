package com.example.MyUniverse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyUniverse.R;
import com.example.MyUniverse.constructors.Posts;
import com.example.MyUniverse.constructors.Utilizador;
import com.example.MyUniverse.daos.PostsDAO;
import com.example.MyUniverse.daos.SeguidoresDAO;
import com.example.MyUniverse.daos.UtilizadoresDAO;

import java.util.ArrayList;

public class FollowsAdapter extends RecyclerView.Adapter<FollowsAdapter.ViewHolder> {

    private ArrayList<Utilizador> followsList;
    Context context;

    public FollowsAdapter(ArrayList<Utilizador> followsList) {
        this.followsList = followsList;
    }

    @NonNull
    @Override
    public FollowsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_follows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String idUser = String.valueOf(followsList.get(position).getLoginId());
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(holder.itemView.getContext());
        String user = String.valueOf(utilizadoresDAO.getUserData(idUser).getNome());


        holder.lblUserFollows.setText(user);
        holder.lblLoginIdFollows.setText(idUser);
    }

    @Override
    public int getItemCount() {
        return followsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblUserFollows,lblLoginIdFollows;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblLoginIdFollows = itemView.findViewById(R.id.lblLoginIdFollows);
            lblUserFollows = itemView.findViewById(R.id.lblUserFollows);
        }
    }
}
