package Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Entity.Noticia;

@Dao
public interface NoticiaDao {
    @Insert
    void insertNoticia(Noticia novaNoticia);

    @Query("SELECT * FROM noticia")
    List<Noticia> findAll();

    @Query("SELECT * FROM noticia ORDER BY data_criacao DESC")
    List<Noticia> findAllOrderByDate();

    @Query("SELECT * FROM noticia WHERE id=:id LIMIT 1")
    Noticia buscaPorId(Long id);

    @Query("SELECT * FROM noticia WHERE id_categoria=:idCategoria ORDER BY data_criacao DESC")
    List<Noticia> buscaPorIdCategoria(Long idCategoria);
}
