package com.example.MyUniverse.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyUniverse.R;
import com.example.MyUniverse.activities.EditarPerfilActivity;
import com.example.MyUniverse.daos.SeguidoresDAO;
import com.example.MyUniverse.daos.UtilizadoresDAO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment {

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

    //user profile id
    String userProfileId;

    //labels
    TextView lblNome, lblDesc, lblPosts, lblFollowers, lblFollowing;

    //users dao
    UtilizadoresDAO utilizadoresDAO;
    SeguidoresDAO seguidoresDAO;

    //buttons
    Button btnSeguir;

    public Perfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil newInstance(String param1, String param2) {
        Perfil fragment = new Perfil();
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
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        utilizadoresDAO = new UtilizadoresDAO(view.getContext());
        seguidoresDAO = new SeguidoresDAO(view.getContext());

        //refer elements
        lblNome = view.findViewById(R.id.lblNomeFragment);
        lblDesc = view.findViewById(R.id.lblDesc);
        lblFollowers = view.findViewById(R.id.lblNFollowers);
        lblFollowing = view.findViewById(R.id.lblNFollowing);
        btnSeguir = view.findViewById(R.id.btnSeguir);

        if(getArguments() != null){
            loginId = getArguments().getString("loginId");
            userProfileId = getArguments().getString("userProfileId");
            lblNome.setText(utilizadoresDAO.getUserData(userProfileId).getNome().toString());
            lblDesc.setText(utilizadoresDAO.getUserData(userProfileId).getDesc().toString());
            lblFollowers.setText(String.valueOf(seguidoresDAO.countFollowers(userProfileId)) + "\n followers");
            lblFollowing.setText(String.valueOf(seguidoresDAO.countFollowing(userProfileId)) + "\n following");
        }

        if(!loginId.equals(userProfileId)){
            if(seguidoresDAO.checkIfUserFollows(loginId, userProfileId)){
                btnSeguir.setText("Following");
            }else{
                btnSeguir.setText("Follow");
            }
        }else{
            btnSeguir.setText("Edit profile");
        }

        btnSeguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnSeguir.getText().equals("Follow")){
                    seguidoresDAO.follow(loginId,userProfileId);
                    btnSeguir.setText("Following");
                }else if(btnSeguir.getText().equals("Following")){
                    seguidoresDAO.unfollow(loginId, userProfileId);
                    btnSeguir.setText("Follow");
                }else if(btnSeguir.getText().equals("Edit profile")){
                    Intent intent = new Intent(getActivity(), EditarPerfilActivity.class);
                    intent.putExtra("loginId", loginId);
                    startActivity(intent);
                }
            }
        });

        lblFollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Followers_or_following followers_or_following = new Followers_or_following();
                Bundle bundle = new Bundle();
                bundle.putString("loginId", loginId);
                bundle.putString("userProfileId", userProfileId);
                bundle.putString("type", "followers");
                followers_or_following.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, followers_or_following).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}