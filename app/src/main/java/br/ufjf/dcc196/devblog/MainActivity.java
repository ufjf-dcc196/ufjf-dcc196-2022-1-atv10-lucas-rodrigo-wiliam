package br.ufjf.dcc196.devblog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import Entity.Categoria;
import Util.Seed;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewPesquisar;
    private RecyclerView recyclerViewCategoria;
    private RecyclerView recyclerViewNoticias;
    private List<Categoria> categorias;
    private CategoriaAdapter categoriaAdapter;
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
    }
}