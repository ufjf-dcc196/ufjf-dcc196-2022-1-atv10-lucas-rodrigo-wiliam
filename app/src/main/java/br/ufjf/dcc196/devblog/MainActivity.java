package br.ufjf.dcc196.devblog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewPesquisar;
    private RecyclerView recyclerViewCategoria;
    private RecyclerView recyclerViewNoticias;
    private List<Categoria> categorias;
    private CategoriaAdapter categoriaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        recyclerViewCategoria = findViewById(R.id.recyclerViewCategoria);


        categorias = new ArrayList<Categoria>(){{
            add(new Categoria(R.drawable.html_icon, "HTML5"));
            add(new Categoria(R.drawable.css_icon, "CSS"));
            add(new Categoria(R.drawable.python_icon, "Python"));
            add(new Categoria(R.drawable.javascript_icon, "JavaScript"));
            add(new Categoria(R.drawable.java_icon, "Java"));
        }};

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