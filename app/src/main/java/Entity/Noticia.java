package Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "noticia" ,
        foreignKeys = @ForeignKey(entity = Categoria.class,
            parentColumns = "id",
            childColumns = "id_categoria",
            onDelete = ForeignKey.CASCADE)
)
public class Noticia {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "titulo")
    private String titulo;

    @ColumnInfo(name = "descricao")
    private String descricao;

    @ColumnInfo(name = "imagem_src")
    private int imagemSrc;

    @ColumnInfo(name = "data_criacao")
    private Date dataCriacao;

    @ColumnInfo(name = "id_categoria")
    private Long idCategoria;

    public Noticia(String titulo, String descricao, int imagemSrc, Date dataCriacao, Long idCategoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagemSrc = imagemSrc;
        this.dataCriacao = dataCriacao;
        this.idCategoria = idCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getImagemSrc() {
        return imagemSrc;
    }

    public void setImagemSrc(int imagemSrc) {
        this.imagemSrc = imagemSrc;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}
