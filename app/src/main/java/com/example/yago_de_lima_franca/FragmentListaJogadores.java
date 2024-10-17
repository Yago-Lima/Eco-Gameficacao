package com.example.yago_de_lima_franca;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yago_de_lima_franca.dao.IJogadorDao;
import com.example.yago_de_lima_franca.model.ConexaoDB;
import com.example.yago_de_lima_franca.model.Jogador;
import com.example.yago_de_lima_franca.model.JogadorAdapter;
import com.example.yago_de_lima_franca.model.PartidaAdapter;

import java.util.List;


public class FragmentListaJogadores extends Fragment {
    private RecyclerView recyclerView;
    private PartidaAdapter partidaAdapter;
    private ImageView buttonFechar;

    private ConexaoDB conexaoDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_jogadores, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        conexaoDB = new ConexaoDB(getContext());

        List<Jogador> jogadores = getjogadores();

        JogadorAdapter jogadorAdapter = new JogadorAdapter(jogadores);
        recyclerView.setAdapter(jogadorAdapter);

        buttonFechar = view.findViewById(R.id.buttonFechar);


        buttonFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().remove(FragmentListaJogadores.this).commit();
            }
        });

        return view;
    }


    private List<Jogador> getjogadores() {
        IJogadorDao iJogadorDao = conexaoDB.getDb().iJogadorDao();
        return iJogadorDao.getJogadores();
    }
}
