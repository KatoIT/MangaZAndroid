package com.example.mangaz.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.R;
import com.example.mangaz.manga.Manga;
import com.example.mangaz.manga.MangaAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private List<Manga> mangaList;
    MangaAdapter.IClickItemListener mIClickItemListener;

    public interface IClickItemListener{ // interface Sự kiện click
        void onClickItemManga(Manga manga);
    }

    public CategoryAdapter(MangaAdapter.IClickItemListener mIClickItemListener) {
        this.mIClickItemListener = mIClickItemListener;
    }

    public void setData(List<Manga> mangaList) {
        this.mangaList = mangaList;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manga_phan_loai_fragment, parent, false);
        return new CategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoryAdapter.CategoryViewHolder holder, int position) {
        final Manga manga = mangaList.get(position);
        if (manga == null) {
            return;
        }
        holder.textViewMangaNameItem.setText(manga.getMangaName()); // set Mame Manga
//        holder.imageViewMangaAvatar.setImageResource(R.drawable.mangaz2); // set Image
        holder.imageViewMangaAvatarItem.setImageResource(Integer.parseInt(manga.getUrlImage())); // set Image
        holder.textViewCategoryInItemManga.setText(manga.getListCategory());
        holder.textViewChapItemManga.setText(manga.getCountChapter() + " Chap");
        holder.linearLayoutItem.setOnClickListener(new View.OnClickListener() {
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

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewMangaNameItem, textViewCategoryInItemManga, textViewChapItemManga;
        private ImageView imageViewMangaAvatarItem;
        private LinearLayout linearLayoutItem;

        public CategoryViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textViewMangaNameItem = itemView.findViewById(R.id.textViewMangaNameItem);
            textViewCategoryInItemManga = itemView.findViewById(R.id.textViewCategoryInItemManga);
            textViewChapItemManga = itemView.findViewById(R.id.textViewChapItemManga);
            imageViewMangaAvatarItem = itemView.findViewById(R.id.imageViewMangaAvatarItem);
            linearLayoutItem = itemView.findViewById(R.id.linearLayoutItem);

        }
    }
}
