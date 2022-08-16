package br.ufjf.dcc196.devblog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import Entity.Categoria;
import Entity.Noticia;

public class NoticiasCategoriaActivity extends AppCompatActivity {
    private AppDatabase db;
    private Categoria categoria;
    private RecyclerView recyclerViewNoticias;
    private List<Noticia> noticias;
    private NoticiaAdapter noticiaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_categoria);
        Bundle extras = getIntent().getExtras();
        db = AppDatabase.getInstance(getApplicationContext());
        categoria = db.categoriaDao().buscarPorId(extras.getLong("categoria"));
        this.setTitle(categoria.getNome());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerViewNoticias = findViewById(R.id.recyclerViewNoticias);
        noticias = db.noticiaDao().buscaPorIdCategoria(categoria.getId());

        LinearLayoutManager layoutManagerNoticia = new LinearLayoutManager(this);
        recyclerViewNoticias.setLayoutManager(layoutManagerNoticia);
        NoticiaAdapter.OnNoticiaClickListener listenerNoticia = new NoticiaAdapter.OnNoticiaClickListener() {
            @Override
            public void onNoticiaClick(View view, int position) {
                Noticia noticia = noticias.get(position);
                Intent intent = new Intent(NoticiasCategoriaActivity.this, NoticiaActivity.class);
                intent.putExtra("idNoticia", noticia.getId());
                startActivity(intent);
            }
        };
        noticiaAdapter = new NoticiaAdapter(noticias, listenerNoticia);
        recyclerViewNoticias.setAdapter(noticiaAdapter);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}