package com.example.escommunity.fragmentos;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.escommunity.R;
import com.example.escommunity.adapters.UsersAdapter;
import com.example.escommunity.constructors.Utilizador;
import com.example.escommunity.daos.UtilizadoresDAO;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link procurar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class procurar extends Fragment {

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
    UtilizadoresDAO utilizadoresDAO;

    ArrayList<Utilizador> listUtilizadores;
    UsersAdapter usersAdapter = new UsersAdapter(listUtilizadores);

    //loginId
    String loginId;


    public procurar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment procurar.
     */
    // TODO: Rename and change types and number of parameters
    public static procurar newInstance(String param1, String param2) {
        procurar fragment = new procurar();
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
        View view = inflater.inflate(R.layout.fragment_procurar, container,false);

        //loginId
        if(getArguments() != null){
            loginId = getArguments().getString("loginId");

        }

        recyclerView = view.findViewById(R.id.rvUtilizadores);
        EditText txtProcurar;
        txtProcurar = view.findViewById(R.id.txtProcurarFragment);

        txtProcurar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(txtProcurar.getText().length() > 0){
                    procurar(txtProcurar.getText().toString());
                }
            }
        });

        return view;

    }

    private void procurar(String arg){
        //buscar a tabela utilizadores
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(getContext());

        //Carregar a recycler view
        ArrayList<Utilizador> listUtilizadores = utilizadoresDAO.procurarUsers(arg);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        UsersAdapter usersAdapter = new UsersAdapter(listUtilizadores);
        //usersAdapter.setUserLoginId(loginId);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setAdapter(){
        //buscar a tabela utilizadores
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(getContext());

        //Carregar a recycler view
        listUtilizadores = utilizadoresDAO.getUtilizadores();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        UsersAdapter usersAdapter = new UsersAdapter(listUtilizadores);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}