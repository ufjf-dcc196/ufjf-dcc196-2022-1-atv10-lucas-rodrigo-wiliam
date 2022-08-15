package Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Entity.Categoria;

@Dao
public interface CategoriaDao {
        @Insert
        void insertCategoria(Categoria novaCategoria);

        @Query("SELECT * FROM categoria")
        List<Categoria> findAll();


}
