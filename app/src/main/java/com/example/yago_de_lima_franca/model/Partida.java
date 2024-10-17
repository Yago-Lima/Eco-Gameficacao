package com.example.yago_de_lima_franca.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.yago_de_lima_franca.utils.ConversorDeEnums;

import java.io.Serializable;

@Entity(
        tableName = "_partida",
        foreignKeys = @ForeignKey(
                entity = Jogador.class,
                parentColumns = "jogador_id",
                childColumns = "jogador_id",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
)
public class Partida implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "partida_id")
    private Long partidaId;

    @ColumnInfo(name = "pontuacao")
    private double pontuacao;

    @ColumnInfo(name = "jogador_id")
    public Long jogador;

    @ColumnInfo(name = "dificuldade")
    @TypeConverters(ConversorDeEnums.class) // Converte a enum para um valor armazenável
    private Dificuldade dificuldade;

    public Partida() {
    }

    public Partida(Long partidaId, double pontuacao, Long jogador, Dificuldade dificuldade) {
        this.partidaId = partidaId;
        this.pontuacao = pontuacao;
        this.jogador = jogador;
        this.dificuldade = dificuldade;
    }
    public Long getPartidaId() {
        return partidaId;
    }

    public double getPontuacao() {
        return pontuacao;
    }


    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Long getJogador() {
        return jogador;
    }

    public void setJogador(Long jogador) {
        this.jogador = jogador;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public void setPartidaId(Long partidaId) {
        this.partidaId = partidaId;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "partidaId=" + partidaId +
                ", pontuacao='" + pontuacao + '\'' +
                ", jogadorId=" + jogador +
                ", dificuldade=" + dificuldade +
                '}';
    }
}

