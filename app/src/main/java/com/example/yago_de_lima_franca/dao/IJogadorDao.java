package com.example.yago_de_lima_franca.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.yago_de_lima_franca.model.Jogador;

import java.util.List;

@Dao
public interface IJogadorDao {
    @Insert
    Long insertJogador(Jogador jogador);

    @Delete
    void deleteJogador(Jogador jogador);

    @Query("SELECT * FROM _jogador")
    List<Jogador> getJogadores();

    @Query("SELECT * FROM _jogador where jogador_id = :id")
    Jogador getJogadorById(long id);

}
