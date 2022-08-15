package Util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Entity.Categoria;
import Entity.Noticia;
import br.ufjf.dcc196.devblog.AppDatabase;
import br.ufjf.dcc196.devblog.R;

public class Seed {
    private List<Categoria> categorias;
    private List<Noticia> noticias;

    public void execSeed(AppDatabase db){
        categorias = db.categoriaDao().findAll();
        noticias = db.noticiaDao().findAll();

        if(categorias.size() == 0 && noticias.size() == 0) {
            db.categoriaDao().insertCategoria(new Categoria("HTML5", R.drawable.icon_html));
            db.categoriaDao().insertCategoria(new Categoria("CSS",R.drawable.icon_css));
            db.categoriaDao().insertCategoria(new Categoria("Python",R.drawable.icon_python));
            db.categoriaDao().insertCategoria(new Categoria("JavaScript",R.drawable.icon_javascript));
            db.categoriaDao().insertCategoria(new Categoria("Java",R.drawable.icon_java));

            Date date = new Date();
            db.noticiaDao().insertNoticia(
                    new Noticia("Notícia HTML 1", "Descrição HTML 1", R.drawable.noticia_html, date, Long.parseLong("1"))
            );
            db.noticiaDao().insertNoticia(
                    new Noticia("Notícia CSS 1", "Descrição CSS 1", R.drawable.noticia_css, date, Long.parseLong("2"))
            );
            db.noticiaDao().insertNoticia(
                    new Noticia("Notícia Python 1", "Descrição Python 1", R.drawable.noticia_python, date, Long.parseLong("3"))
            );
            db.noticiaDao().insertNoticia(
                    new Noticia("Notícia JavaScript 1", "Descrição JavaScript 1", R.drawable.noticia_javascript, date, Long.parseLong("4"))
            );
            db.noticiaDao().insertNoticia(
                    new Noticia("Notícia Java 1", "Descrição Java 1", R.drawable.noticia_java, date, Long.parseLong("5"))
            );

            // Reduzindo 1 dia da data de hoje
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, -1);
            date = c.getTime();

            db.noticiaDao().insertNoticia(
                    new Noticia("Notícia HTML 2", "Descrição HTML 2", R.drawable.noticia_html, date, Long.parseLong("1"))
            );
            db.noticiaDao().insertNoticia(
                    new Noticia("Notícia CSS 2", "Descrição CSS 2", R.drawable.noticia_css, date, Long.parseLong("2"))
            );
            db.noticiaDao().insertNoticia(
                    new Noticia("Notícia Python 2", "Descrição Python 2", R.drawable.noticia_python, date, Long.parseLong("3"))
            );
            db.noticiaDao().insertNoticia(
                    new Noticia("Notícia JavaScript 2", "Descrição JavaScript 2", R.drawable.noticia_javascript, date, Long.parseLong("4"))
            );
            db.noticiaDao().insertNoticia(
                    new Noticia("Notícia Java 2", "Descrição Java 2", R.drawable.noticia_java, date, Long.parseLong("5"))
            );

        }
    }
}
