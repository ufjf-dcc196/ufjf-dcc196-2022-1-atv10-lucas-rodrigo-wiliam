package Util;

import java.util.List;

import Entity.Categoria;
import br.ufjf.dcc196.devblog.AppDatabase;
import br.ufjf.dcc196.devblog.R;

public class Seed {
    private List<Categoria> categorias;

    public void execSeed(AppDatabase db){
        categorias = db.categoriaDao().findAll();

        if(categorias.size() == 0) {
            db.categoriaDao().insertCategoria(new Categoria("HTML5", R.drawable.html_icon));
            db.categoriaDao().insertCategoria(new Categoria("CSS",R.drawable.css_icon));
            db.categoriaDao().insertCategoria(new Categoria("Python",R.drawable.python_icon));
            db.categoriaDao().insertCategoria(new Categoria("JavaScript",R.drawable.javascript_icon));
            db.categoriaDao().insertCategoria(new Categoria("Java",R.drawable.java_icon));
        }
    }
}
