package com.example.yago_de_lima_franca.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yago_de_lima_franca.R;
import com.example.yago_de_lima_franca.dao.IJogadorDao;
import com.example.yago_de_lima_franca.model.Jogador;

import java.util.List;

public class JogadorAdapter extends RecyclerView.Adapter<JogadorAdapter.JogadorViewHolder> {

    private List<Jogador> jogadorList;
    private ConexaoDB db;
    private IJogadorDao iJogadorDao;

    public JogadorAdapter(List<Jogador> jogadorList) {
        this.jogadorList = jogadorList;
    }

    @NonNull
    @Override
    public JogadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jogador, parent, false);
        return new JogadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JogadorViewHolder holder, int position) {
        Jogador jogador = jogadorList.get(position);
        holder.textViewJogadorId.setText("ID: " + jogador.getJogadorId());
        holder.textViewJogadorNome.setText("Nome: " + jogador.getJogadorName());
        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new ConexaoDB(holder.itemView.getContext());
                iJogadorDao = db.getDb().iJogadorDao();
                iJogadorDao.deleteJogador(jogador);
                jogadorList.remove(jogador);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jogadorList.size();
    }

    public static class JogadorViewHolder extends RecyclerView.ViewHolder {

        TextView textViewJogadorId;
        TextView textViewJogadorNome;
        Button button_delete;

        public JogadorViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewJogadorId = itemView.findViewById(R.id.textView_jogadorId);
            textViewJogadorNome = itemView.findViewById(R.id.textView_jogadorNome);
            button_delete = itemView.findViewById(R.id.button_delete);
        }
    }
}
