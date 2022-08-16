package br.ufjf.dcc196.devblog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import Entity.Categoria;
import Entity.Noticia;
import Util.Seed;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewPesquisar;
    private RecyclerView recyclerViewCategoria;
    private RecyclerView recyclerViewNoticias;
    private List<Categoria> categorias;
    private List<Noticia> noticias;
    private CategoriaAdapter categoriaAdapter;
    private NoticiaAdapter noticiaAdapter;
    private AppDatabase db;
    private Seed seed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        db = AppDatabase.getInstance(getApplicationContext());
        seed = new Seed();
        seed.execSeed(db);

        recyclerViewCategoria = findViewById(R.id.recyclerViewCategoria);

        categorias = db.categoriaDao().findAll();

        LinearLayoutManager layoutManagerCategoria = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoria.setLayoutManager(layoutManagerCategoria);
        CategoriaAdapter.OnCategoriaClickListener listenerCategoria = new CategoriaAdapter.OnCategoriaClickListener() {
            @Override
            public void onCategoriaClick(View view, int position) {
                Categoria categoria = categorias.get(position);
                Intent intent = new Intent(MainActivity.this, NoticiasCategoriaActivity.class);
                intent.putExtra("categoria", categoria.getNome());
                startActivity(intent);
            }
        };
        categoriaAdapter = new CategoriaAdapter(categorias, listenerCategoria);
        recyclerViewCategoria.setAdapter(categoriaAdapter);

        recyclerViewNoticias = findViewById(R.id.recyclerViewNoticias);
        noticias = db.noticiaDao().findAllOrderByDate();

        LinearLayoutManager layoutManagerNoticia = new LinearLayoutManager(this);
        recyclerViewNoticias.setLayoutManager(layoutManagerNoticia);
        NoticiaAdapter.OnNoticiaClickListener listenerNoticia = new NoticiaAdapter.OnNoticiaClickListener() {
            @Override
            public void onNoticiaClick(View view, int position) {
                Noticia noticia = noticias.get(position);
                Intent intent = new Intent(MainActivity.this, NoticiaActivity.class);
                intent.putExtra("idNoticia", noticia.getId());
                startActivity(intent);
            }
        };
        noticiaAdapter = new NoticiaAdapter(noticias, listenerNoticia);
        recyclerViewNoticias.setAdapter(noticiaAdapter);

        for(Categoria categoria : categorias) {
            Log.e("Categoria", Long.toString(categoria.getId()));
        }

        for(Noticia noticia : noticias) {
            Log.e("Noticia", Long.toString(noticia.getIdCategoria()));
        }
    }
}