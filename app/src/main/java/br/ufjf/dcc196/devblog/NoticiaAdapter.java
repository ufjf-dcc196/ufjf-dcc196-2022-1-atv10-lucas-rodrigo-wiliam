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

import Entity.Noticia;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder> {
    private List<Noticia> noticias;
    private OnNoticiaClickListener listener;

    public NoticiaAdapter(List<Noticia> noticias, OnNoticiaClickListener listener) {
        this.noticias = noticias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoticiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context contexto = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View tipoView = inflater.inflate(R.layout.noticia_layout, parent, false);
        NoticiaViewHolder viewHolder = new NoticiaViewHolder(tipoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiaViewHolder holder, int position) {
        Noticia noticia = noticias.get(position);
        holder.imageViewNoticia.setImageResource(noticia.getImagemSrc());
        holder.textViewTitle.setText(noticia.getTitulo());
        holder.textViewDescricao.setText(noticia.getDescricao());

    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }


    public class NoticiaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewNoticia;
        private TextView textViewTitle;
        private TextView textViewDescricao;

        public NoticiaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewNoticia = itemView.findViewById(R.id.imageViewNoticia);
            textViewTitle = itemView.findViewById(R.id.textViewTituloFav);
            textViewDescricao = itemView.findViewById(R.id.textViewDescricao);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onNoticiaClick(view, getAdapterPosition());
                }
            });
        }
    }

    public interface OnNoticiaClickListener{
        void onNoticiaClick(View view, int position);
    }
}
