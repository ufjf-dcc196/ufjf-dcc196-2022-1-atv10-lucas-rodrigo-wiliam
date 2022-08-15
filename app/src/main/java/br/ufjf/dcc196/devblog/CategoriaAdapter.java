package br.ufjf.dcc196.devblog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Entity.Categoria;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {
    private List<Categoria> categorias;
    private OnCategoriaClickListener listener;

    public CategoriaAdapter(List<Categoria> categorias, OnCategoriaClickListener listener) {
        this.categorias = categorias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriaAdapter.CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context contexto = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View tipoView = inflater.inflate(R.layout.categoria_noticia_layout, parent, false);
        CategoriaViewHolder viewHolder = new CategoriaViewHolder(tipoView);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaAdapter.CategoriaViewHolder holder, int position) {
        Categoria tipoNoticia = categorias.get(position);
        holder.imageViewTipo.setImageResource(tipoNoticia.getImagemSrc());
        holder.textViewTipo.setText(tipoNoticia.getNome());
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }


    public class CategoriaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewTipo;
        private TextView textViewTipo;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewTipo = itemView.findViewById(R.id.imageViewTipo);
            textViewTipo = itemView.findViewById(R.id.textViewTipo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCategoriaClick(view, getAdapterPosition());
                }
            });
        }
    }

    public interface OnCategoriaClickListener{
        void onCategoriaClick(View view, int position);
    }
}
