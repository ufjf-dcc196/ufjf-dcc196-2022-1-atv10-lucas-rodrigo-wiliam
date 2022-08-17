package Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Entity.Categoria;

@Dao
public interface CategoriaDao {
        @Insert
        void insertCategoria(Categoria novaCategoria);

        @Update
        void updateCategoria(Categoria categoria);

        @Query("SELECT * FROM categoria")
        List<Categoria> findAll();

        @Query("SELECT * FROM categoria WHERE id=:id LIMIT 1")
        Categoria buscarPorId(Long id);

        @Query("SELECT * FROM categoria WHERE favorito = 1 LIMIT 1")
        Categoria findCategoriaFavoritada();

}
