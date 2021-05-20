/*
 * Coder: Nguyen Van An
 * Date: 11-5-2021
 * Content: Custom Adapter Manga cho RecyclerView
 *      + Xử lý sự kiện Click Item Manga
 *
 *
 * */
package com.example.mangaz.manga;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.Model.Manga;
import com.example.mangaz.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MangaViewHolder> {
    private List<Manga> mangaList;
    IClickItemListener mIClickItemListener;

    public interface IClickItemListener { // interface Sự kiện click
        void onClickItemManga(Manga manga);
    }

    public MangaAdapter(IClickItemListener mIClickItemListener) {
        this.mIClickItemListener = mIClickItemListener;
    }

    public void setData(List<Manga> mangaList) {
        this.mangaList = mangaList;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public MangaViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manga_home_fragment, parent, false);
        return new MangaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MangaViewHolder holder, int position) {
        final Manga manga = mangaList.get(position);
        if (manga == null) {
            return;
        }
        holder.textViewMangaName.setText(manga.getMangaName()); // set Mame Manga
        if (manga.getUrlImage().length() < 2) {
            holder.imageViewMangaAvatar.setImageBitmap(manga.getAvatar());
        } else {
            holder.imageViewMangaAvatar.setImageResource(Integer.parseInt(manga.getUrlImage()));
        }
        holder.cardViewItemManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickItemListener.onClickItemManga(manga);// Sự kiện Click
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mangaList != null) {
            return mangaList.size();
        }
        return 0;
    }

    public class MangaViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewMangaName;
        private ImageView imageViewMangaAvatar;
        private CardView cardViewItemManga;

        public MangaViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textViewMangaName = itemView.findViewById(R.id.textViewItemMangaName);
            imageViewMangaAvatar = itemView.findViewById(R.id.imageViewItemMangaAvatar);
            cardViewItemManga = itemView.findViewById(R.id.cardViewItemManga);

        }
    }
}
