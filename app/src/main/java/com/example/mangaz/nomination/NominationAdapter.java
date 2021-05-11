/*
 * Coder: Nguyen Van An
 * Date: 11-5-2021
 * Content: Custom Adapter Nomination cho RecyclerView
 *      + Xử lý sự kiện Click Item Manga
 *
 *
 * */
package com.example.mangaz.nomination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.R;
import com.example.mangaz.manga.Manga;
import com.example.mangaz.manga.MangaAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NominationAdapter extends RecyclerView.Adapter<NominationAdapter.NominationViewHolder> {
    private Context mContext;
    private List<Nomination> nominationList;
    private IClickItem mIClickItem;

    public interface IClickItem { // interface Sự kiện click
        void onClick(Manga manga);
    }

    public NominationAdapter(Context mContext, IClickItem mIClickItem) {
        this.mContext = mContext;
        this.mIClickItem = mIClickItem;
    }

    public void setData(List<Nomination> nominationList) { // update data RecyclerView
        this.nominationList = nominationList;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public NominationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nominations_home_fragment, parent, false);
        return new NominationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NominationViewHolder holder, int position) {
        Nomination nomination = nominationList.get(position);
        if (nomination == null) {
            return;
        }
        holder.textViewNominationName.setText(nomination.getNominationName());
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.recyclerViewNominationManga.setLayoutManager(mLinearLayoutManager); // set HORIZONTAL on RecyclerView

        MangaAdapter mangaAdapter = new MangaAdapter(new MangaAdapter.IClickItemListener() {
            @Override
            public void onClickItemManga(Manga manga) {
                mIClickItem.onClick(manga); // Sự kiện Click
            }
        });
        mangaAdapter.setData(nomination.getMangaList());
        holder.recyclerViewNominationManga.setAdapter(mangaAdapter);
    }

    @Override
    public int getItemCount() {
        if (nominationList != null) {
            return nominationList.size();
        }
        return 0;
    }

    public class NominationViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNominationName;
        private RecyclerView recyclerViewNominationManga;

        public NominationViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            // Ánh xạ
            textViewNominationName = itemView.findViewById(R.id.textViewNominationName);
            recyclerViewNominationManga = itemView.findViewById(R.id.recyclerViewNominationManga);
        }
    }
}
