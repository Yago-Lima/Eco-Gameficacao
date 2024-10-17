package com.example.yago_de_lima_franca.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yago_de_lima_franca.R;
import com.example.yago_de_lima_franca.dao.IJogadorDao;
import com.example.yago_de_lima_franca.model.ConexaoDB;
import com.example.yago_de_lima_franca.model.Jogador;
import com.example.yago_de_lima_franca.model.Partida;
import com.example.yago_de_lima_franca.utils.ConversorDeEnums;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PartidaAdapter extends RecyclerView.Adapter<PartidaAdapter.PartidaViewHolder> {

    private List<Partida> partidaComJogadorList;
    private ConexaoDB db;
    private IJogadorDao iJogadorDao;

    public PartidaAdapter(List<Partida> partidaComJogadorList) {
        Collections.sort(partidaComJogadorList, new Comparator<Partida>() {
            @Override
            public int compare(Partida p1, Partida p2) {
                return Double.compare(p2.getPontuacao(), p1.getPontuacao());
            }
        });
        this.partidaComJogadorList = partidaComJogadorList;
    }

    @NonNull
    @Override
    public PartidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partida, parent, false);
        return new PartidaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidaViewHolder holder, int position) {
        Partida partidaComJogador = partidaComJogadorList.get(position);
        db = new ConexaoDB(holder.itemView.getContext());
        iJogadorDao = db.getDb().iJogadorDao();
        Jogador jogador = iJogadorDao.getJogadorById(partidaComJogador.getJogador());
        holder.jogadorNome.setText(jogador.getJogadorName());
        holder.jogadorNome.setText(jogador.getJogadorName());
        holder.dificuldade.setText(ConversorDeEnums.fromDificuldade(partidaComJogador.getDificuldade()));
        holder.pontuacao.setText(String.format("%.2f", partidaComJogador.getPontuacao()));
    }

    @Override
    public int getItemCount() {
        return partidaComJogadorList != null ? partidaComJogadorList.size() : 0;
    }


    public class PartidaViewHolder extends RecyclerView.ViewHolder {
        TextView jogadorNome;
        TextView pontuacao;
        TextView dificuldade;

        public PartidaViewHolder(@NonNull View itemView) {
            super(itemView);
            jogadorNome = itemView.findViewById(R.id.jogador_nome);
            pontuacao = itemView.findViewById(R.id.pontuacao);
            dificuldade = itemView.findViewById(R.id.dificuldade);
        }
    }
}
