package Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categoria")
public class Categoria {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "nome")
    private String nome;

    @ColumnInfo(name = "imagem_src")
    private int imagemSrc;


    public Categoria(String nome, int imagemSrc) {
        this.nome = nome;
        this.imagemSrc = imagemSrc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagemSrc() {
        return imagemSrc;
    }

    public void setImagemSrc(int imagemSrc) {
        this.imagemSrc = imagemSrc;
    }
}
