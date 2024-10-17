package com.example.yago_de_lima_franca;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class FragmentMenu extends Fragment {

    private Button buttonVerJogadores;
    private Button buttonVerPontuacao;
    private ImageView buttonFechar;
    private FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // Inicializar o FragmentManager
        fm = getParentFragmentManager();

        buttonVerJogadores = view.findViewById(R.id.buttonVerJogadores);
        buttonVerPontuacao = view.findViewById(R.id.buttonVerPontuacao);

        buttonVerJogadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentListaJogadores fragmentListaJogadores = new FragmentListaJogadores();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.telaPrincipal, fragmentListaJogadores);
                ft.commit();
            }
        });

        buttonVerPontuacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentListaPartida fragmentListaPartida = new FragmentListaPartida();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.telaPrincipal, fragmentListaPartida);
                ft.commit();
            }
        });

        buttonFechar = view.findViewById(R.id.buttonFechar);


        buttonFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().remove(FragmentMenu.this).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
