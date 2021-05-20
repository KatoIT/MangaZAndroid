package com.example.mangaz.category;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.Database;
import com.example.mangaz.Model.Manga;
import com.example.mangaz.R;
import com.example.mangaz.manga.MangaAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Manga> mangaList;
    private Database db;
    private Context mContext;
    MangaAdapter.IClickItemListener mIClickItemListener;

    public interface IClickItemListener { // interface Sự kiện click
        void onClickItemManga(Manga manga);
    }

    public CategoryAdapter(Context mContext, MangaAdapter.IClickItemListener mIClickItemListener) {
        this.mIClickItemListener = mIClickItemListener;
        this.mContext = mContext;
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
        if (manga.getUrlImage().length() < 2) {
            holder.imageViewMangaAvatarItem.setImageBitmap(manga.getAvatar());
        } else {
            holder.imageViewMangaAvatarItem.setImageResource(Integer.parseInt(manga.getUrlImage()));
        }
        db = new Database(mContext);
        String mCategory = "";
        Cursor cursor = db.GetListCategoryByMangaName(manga.getMangaName());
        while (cursor.moveToNext()) {
            if (mCategory.isEmpty()) {
                mCategory = cursor.getString(0);
            } else {
                mCategory += "/" + cursor.getString(0);
            }
        }
        cursor = db.GetChapterByMangaName(manga.getMangaName());
        holder.textViewCategoryInItemManga.setText(mCategory);
        holder.textViewChapItemManga.setText(cursor.getCount() + " Chap");
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
