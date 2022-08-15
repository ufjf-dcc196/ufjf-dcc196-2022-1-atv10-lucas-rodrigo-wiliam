package br.ufjf.dcc196.devblog;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import Dao.CategoriaDao;
import Dao.NoticiaDao;
import Entity.Categoria;
import Entity.Noticia;
import Util.Converter;

@Database(entities = {Categoria.class, Noticia.class}, version = 1)
@TypeConverters(Converter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public static final String DATABASE_NAME = "devblog-db";

    public abstract CategoriaDao categoriaDao();
    public abstract NoticiaDao noticiaDao();

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}