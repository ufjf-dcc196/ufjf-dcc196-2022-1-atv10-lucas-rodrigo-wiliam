package br.ufjf.dcc196.devblog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entity.Categoria;
import Entity.Noticia;
import Util.Seed;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewPesquisar;
    private RecyclerView recyclerViewCategoria;
    private RecyclerView recyclerViewNoticias;
    private RecyclerView recyclerViewFavoritos;
    private ItemTouchHelper.SimpleCallback touchHelperCallback;
    private List<Categoria> categorias;
    private List<Noticia> noticias;
    private List<Noticia> noticiasFav;
    private CategoriaAdapter categoriaAdapter;
    private NoticiaAdapter noticiaAdapter;
    private NoticiaFavAdapter noticiaFavAdapter;
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
        CategoriaAdapter.OnCategoriaClickListener listenerCategoriaClick = new CategoriaAdapter.OnCategoriaClickListener() {
            @Override
            public void onCategoriaClick(View view, int position) {
                Categoria categoria = categorias.get(position);
                Intent intent = new Intent(MainActivity.this, NoticiasCategoriaActivity.class);
                intent.putExtra("categoria", categoria.getId());
                startActivity(intent);
            }
        };
        CategoriaAdapter.OnCategoriaLongClickListener listenerCategoriaLongClick = new CategoriaAdapter.OnCategoriaLongClickListener() {
            @Override
            public void onCategoriaLongClick(View view, int position) {
                Categoria categoria = categorias.get(position);
                Categoria favoritoAntigo = db.categoriaDao().findCategoriaFavoritada();

                boolean mostrarNoticias = false;
                if(favoritoAntigo == null){
                    categoria.setFavorito(true);
                    db.categoriaDao().updateCategoria(categoria);
                    mostrarNoticias = true;
                }else{
                    favoritoAntigo.setFavorito(false);
                    db.categoriaDao().updateCategoria(favoritoAntigo);

                    if(categoria.getId() != favoritoAntigo.getId()) {
                        categoria.setFavorito(true);
                        db.categoriaDao().updateCategoria(categoria);
                        mostrarNoticias = true;
                    }
                }

                categorias.clear();
                categorias.addAll(db.categoriaDao().findAll());
                categoriaAdapter.notifyDataSetChanged();

                noticiasFav.clear();
                if(mostrarNoticias) {
                    noticiasFav.addAll(db.noticiaDao().buscaPorIdCategoria(categoria.getId()));
                }
                noticiaFavAdapter.notifyDataSetChanged();
                recyclerViewFavoritos.scrollToPosition(0);
            }
        };
        categoriaAdapter = new CategoriaAdapter(categorias, listenerCategoriaClick, listenerCategoriaLongClick);
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




        recyclerViewFavoritos = findViewById(R.id.recyclerViewFavoritos);
        noticiasFav = new ArrayList<Noticia>();
        Categoria categoriaFavoritada = db.categoriaDao().findCategoriaFavoritada();
        if(categoriaFavoritada != null) {
            noticiasFav.addAll(db.noticiaDao().buscaPorIdCategoria(categoriaFavoritada.getId()));
        }
        LinearLayoutManager layoutManagerNoticiaFav = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFavoritos.setLayoutManager(layoutManagerNoticiaFav);
        NoticiaFavAdapter.OnNoticiaFavClickListener listenerNoticiaFav = new NoticiaFavAdapter.OnNoticiaFavClickListener() {
            @Override
            public void onNoticiaFavClick(View view, int position) {
                Noticia noticia = noticias.get(position);
                Intent intent = new Intent(MainActivity.this, NoticiaActivity.class);
                intent.putExtra("idNoticia", noticia.getId());
                startActivity(intent);
            }
        };
        noticiaFavAdapter = new NoticiaFavAdapter(noticiasFav, listenerNoticiaFav);
        recyclerViewFavoritos.setAdapter(noticiaFavAdapter);
    }

    public void pesquisarClick(View view) {
        Intent intent = new Intent(MainActivity.this, NoticiaBuscaActivity.class);
        startActivity(intent);
    }
}