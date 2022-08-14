package br.ufjf.dcc196.devblog;

public class Categoria {
    private int imagemSrc;
    private String nome;

    public Categoria(int imagemSrc, String nome) {
        this.imagemSrc = imagemSrc;
        this.nome = nome;
    }

    public int getImagemSrc() {
        return imagemSrc;
    }

    public void setImagemSrc(int imagemSrc) {
        this.imagemSrc = imagemSrc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
