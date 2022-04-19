package com.example.MyUniverse.fragmentos;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.MyUniverse.R;
import com.example.MyUniverse.adapters.FollowsAdapter;
import com.example.MyUniverse.constructors.Utilizador;
import com.example.MyUniverse.daos.SeguidoresDAO;
import com.example.MyUniverse.daos.UtilizadoresDAO;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Followers_or_following#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Followers_or_following extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    RecyclerView.Adapter adaptador;
    RecyclerView.LayoutManager layoutManager;

    //loginId
    String loginId;

    //Are we seeing the followers or the following page?
    String type;

    //User login id profile
    String userProfileId;

    //bundle
    Bundle dados;

    public Followers_or_following() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment followers_or_following.
     */
    // TODO: Rename and change types and number of parameters
    public static Followers_or_following newInstance(String param1, String param2) {
        Followers_or_following fragment = new Followers_or_following();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followers_or_following, container, false);
        recyclerView = view.findViewById(R.id.rv_users);
        dados = getArguments();
        if(dados != null){
            loginId = dados.getString("loginId");
            userProfileId = dados.getString("userProfileId");
            type = dados.getString("type");
        }
        setAdapter(view.getContext());

        return view;
    }

    private void setAdapter(Context context){
        SeguidoresDAO seguidoresDAO = new SeguidoresDAO(context);
        if(type.equals("followers")){
            ArrayList<Utilizador> followsList = seguidoresDAO.getUserFollowers(userProfileId);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            FollowsAdapter followsAdapter = new FollowsAdapter(followsList);
            recyclerView.setAdapter(followsAdapter);
            recyclerView.setLayoutManager(layoutManager);

            Toast.makeText(context, followsList.get(0).getNome(), Toast.LENGTH_LONG).show();
        }
    }
}