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

        @Query("SELECT * FROM categoria WHERE id=:id LIMIT 1")
        Categoria buscarPorId(Long id);
}
