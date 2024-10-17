package com.example.yago_de_lima_franca.model;

import android.content.Context;

import androidx.room.Room;

import com.example.yago_de_lima_franca.dao.MyDatabase;

public class ConexaoDB {
    private  MyDatabase db;

    public ConexaoDB(Context context) {
        db = Room.databaseBuilder(context, MyDatabase.class, "my_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public MyDatabase getDb() {
        return db;
    }
}
