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

public class NoticiaFavAdapter extends RecyclerView.Adapter<NoticiaFavAdapter.NoticiaFavViewHolder> {
    private List<Noticia> noticias;
    private NoticiaFavAdapter.OnNoticiaFavClickListener listener;

    public NoticiaFavAdapter(List<Noticia> noticias, NoticiaFavAdapter.OnNoticiaFavClickListener listener) {
        this.noticias = noticias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoticiaFavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context contexto = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View tipoView = inflater.inflate(R.layout.noticia_favorita_layout, parent, false);
        NoticiaFavAdapter.NoticiaFavViewHolder viewHolder = new NoticiaFavAdapter.NoticiaFavViewHolder(tipoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiaFavViewHolder holder, int position) {
        Noticia noticia = noticias.get(position);
        holder.imageViewImgFav.setImageResource(noticia.getImagemSrc());
        holder.textViewTitleFav.setText(noticia.getTitulo());
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }


    public class NoticiaFavViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewImgFav;
        private TextView textViewTitleFav;

        public NoticiaFavViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewImgFav = itemView.findViewById(R.id.imageViewImgFav);
            textViewTitleFav = itemView.findViewById(R.id.textViewTituloFav);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onNoticiaFavClick(view, getAdapterPosition());
                }
            });
        }
    }

    public interface OnNoticiaFavClickListener{
        void onNoticiaFavClick(View view, int position);
    }
}
