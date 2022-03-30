package com.example.escommunity.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.escommunity.R;
import com.example.escommunity.daos.PostsDAO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link novoPost#newInstance} factory method to
 * create an instance of this fragment.
 */
public class novoPost extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Buttons
    Button btnPublicar;

    //txtBox
    EditText txtNovoPost;

    //Bottom navigation bar
    BottomNavigationView bottomNavigationView;

    //loginId
    String loginId;


    public novoPost() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment novoPost.
     */
    // TODO: Rename and change types and number of parameters
    public static novoPost newInstance(String param1, String param2) {
        novoPost fragment = new novoPost();
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
        View view = inflater.inflate(R.layout.fragment_novo_post, container, false);

        //loginId
        Bundle bundle = this.getArguments();
        loginId = bundle.getString("loginId");

        //buscar a tabela posts
        PostsDAO postsDAO = new PostsDAO(getContext());

        btnPublicar = view.findViewById(R.id.btnPublicarFragment);

        //Caixas de texto
        txtNovoPost = view.findViewById(R.id.txtNovoPostFragment);

        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNovoPost.getText().length() == 0) {
                    Toast.makeText(getContext(), "Tem escrever alguma coisa.", Toast.LENGTH_LONG).show();
                    return;
                }
/*                //Buscar a data atual
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                String dia = sdf.format(calendar.getTime());
                //Guardar o post na base de dados
                postsDAO.guardarPost(loginId, txtNovoPost.getText().toString(), dia);
                txtNovoPost.setText("");
                bottomNavigationView = view.findViewById(R.id.menuBottom);
                //bottomNavigationView.setSelectedItemId(R.id.pagina_inicial);*/
                Toast.makeText(getContext(), "Login id: " + loginId, Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }
}