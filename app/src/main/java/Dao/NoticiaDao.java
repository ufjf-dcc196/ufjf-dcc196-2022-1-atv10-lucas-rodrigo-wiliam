package Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Entity.Noticia;

@Dao
public interface NoticiaDao {
    @Insert
    void insertNoticia(Noticia novaNoticia);

    @Query("SELECT * FROM noticia")
    List<Noticia> findAll();

    @Query("SELECT * FROM noticia WHERE id=:id LIMIT 1")
    Noticia buscaPorId(Long id);
}
