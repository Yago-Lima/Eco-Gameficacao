package com.example.yago_de_lima_franca.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.yago_de_lima_franca.model.Jogador;
import com.example.yago_de_lima_franca.model.Partida;


@Database(entities = {Jogador.class, Partida.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract IJogadorDao iJogadorDao();
    public abstract  IpartidaDao ipartidaDao();
}
