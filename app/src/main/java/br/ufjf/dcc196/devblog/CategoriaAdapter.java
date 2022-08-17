package br.ufjf.dcc196.devblog;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Entity.Categoria;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {
    private List<Categoria> categorias;
    private OnCategoriaClickListener listenerClick;
    private OnCategoriaLongClickListener listenerLongClick;

    public CategoriaAdapter(List<Categoria> categorias, OnCategoriaClickListener listenerClick, OnCategoriaLongClickListener listenerLongClick) {
        this.categorias = categorias;
        this.listenerClick = listenerClick;
        this. listenerLongClick = listenerLongClick;
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
        if (tipoNoticia.isFavorito()){
            holder.constraintLayoutItem.setBackgroundResource(R.drawable.categoria_noticia_favorita_background);
        }
        else{
            holder.constraintLayoutItem.setBackgroundResource(R.drawable.categoria_noticia_background);
        }

    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }


    public class CategoriaViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayoutItem;
        private ImageView imageViewTipo;
        private TextView textViewTipo;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayoutItem = itemView.findViewById(R.id.constraintLayoutItem);
            imageViewTipo = itemView.findViewById(R.id.imageViewTipo);
            textViewTipo = itemView.findViewById(R.id.textViewTipo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerClick.onCategoriaClick(view, getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listenerLongClick.onCategoriaLongClick(view, getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public interface OnCategoriaClickListener{
        void onCategoriaClick(View view, int position);
    }

    public interface OnCategoriaLongClickListener{
        void onCategoriaLongClick(View view, int position);
    }
}
