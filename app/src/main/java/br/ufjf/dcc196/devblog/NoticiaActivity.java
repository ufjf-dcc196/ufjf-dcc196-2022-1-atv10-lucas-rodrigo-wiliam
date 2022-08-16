package br.ufjf.dcc196.devblog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import Dao.NoticiaDao;
import Entity.Categoria;
import Entity.Noticia;

public class NoticiaActivity extends AppCompatActivity {
    private AppDatabase db;
    private Noticia noticia;
    private Categoria categoria;

    private ImageView imageViewIMG;
    private TextView textViewCategoriaData;
    private TextView textViewTitulo;
    private TextView textViewDescricao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        db = AppDatabase.getInstance(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        noticia = db.noticiaDao().buscaPorId(extras.getLong("idNoticia"));
        this.setTitle("Detalhes");

        categoria = db.categoriaDao().buscarPorId(noticia.getIdCategoria());

        imageViewIMG = findViewById(R.id.imageViewImg);
        textViewCategoriaData = findViewById(R.id.textViewCategoriaData);
        textViewTitulo = findViewById(R.id.textViewTitulo);
        textViewDescricao = findViewById(R.id.textViewDescricao);

        imageViewIMG.setImageResource(noticia.getImagemSrc());
        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        textViewCategoriaData.setText(categoria.getNome() + " - " + formatador.format(noticia.getDataCriacao()));
        textViewTitulo.setText(noticia.getTitulo());
        textViewDescricao.setText(noticia.getDescricao());
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}