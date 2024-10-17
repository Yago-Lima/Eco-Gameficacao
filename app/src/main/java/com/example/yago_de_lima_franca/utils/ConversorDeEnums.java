package com.example.yago_de_lima_franca.utils;

import androidx.room.TypeConverter;

import com.example.yago_de_lima_franca.model.Dificuldade;

public class ConversorDeEnums {
    @TypeConverter
    public static Dificuldade fromString(String value) {
        return value == null ? null : Dificuldade.valueOf(value);
    }

    @TypeConverter
    public static String fromDificuldade(Dificuldade dificuldade) {
        return dificuldade == null ? null : dificuldade.name();
    }
}
