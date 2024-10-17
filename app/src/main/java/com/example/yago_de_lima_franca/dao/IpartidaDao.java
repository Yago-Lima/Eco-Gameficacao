package com.example.yago_de_lima_franca.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.yago_de_lima_franca.model.Partida;

import java.util.List;
@Dao
public interface IpartidaDao {
        @Insert
        Long insertPartida(Partida partida);

        @Transaction
        @Query("SELECT * FROM _partida")
        List<Partida> getPartidasComJogadores();


    }
