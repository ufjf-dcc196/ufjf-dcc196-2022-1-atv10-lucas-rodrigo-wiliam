package br.ufjf.dcc196.devblog;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import Entity.Noticia;

public class NoticiaBuscaActivity extends AppCompatActivity {
    private AppDatabase db;
    private EditText editTextPesquisar;
    private RecyclerView recyclerViewNoticias;
    private List<Noticia> noticias;
    private NoticiaAdapter noticiaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_busca);
        // Bundle extras = getIntent().getExtras();
        this.setTitle("Procurar");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        editTextPesquisar = findViewById(R.id.editTextPesquisar);
        db = AppDatabase.getInstance(getApplicationContext());
        recyclerViewNoticias = findViewById(R.id.recyclerViewNoticias);
        noticias = new ArrayList<>();

        LinearLayoutManager layoutManagerNoticia = new LinearLayoutManager(this);
        recyclerViewNoticias.setLayoutManager(layoutManagerNoticia);
        NoticiaAdapter.OnNoticiaClickListener listenerNoticia = new NoticiaAdapter.OnNoticiaClickListener() {
            @Override
            public void onNoticiaClick(View view, int position) {
                Noticia noticia = noticias.get(position);
                Intent intent = new Intent(NoticiaBuscaActivity.this, NoticiaActivity.class);
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

    public void buscarNoticias(View view){
        noticias.clear();
        noticias.addAll(db.noticiaDao().buscaPorTitulo(editTextPesquisar.getText().toString()));
        noticiaAdapter.notifyDataSetChanged();
    }
}