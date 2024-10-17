package com.example.yago_de_lima_franca.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "_jogador")
public class Jogador implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "jogador_id")
    private Long jogadorId;
    @NonNull
    @ColumnInfo(name = "jogar_name")
    private String JogadorName;



    public Jogador(long l, String s){}
    public Jogador(){}

    public Long getJogadorId() {
        return jogadorId;
    }

    public void setJogadorId(Long jogadorId) {
        this.jogadorId = jogadorId;
    }

    @Ignore
    public Jogador(String nome) {
        this.JogadorName = nome;
    }

    @NonNull
    public String getJogadorName() {
        return JogadorName;
    }


    public void setJogadorName(@NonNull String jogador) {
        JogadorName = jogador;
    }

    @Ignore
    @Override
    public String toString() {
        return "Jogador{" +
                "jogadorId=" + jogadorId +
                ", JogadorName='" + JogadorName + '\'' +
                '}';
    }
}
