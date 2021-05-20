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
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.Model.Manga;
import com.example.mangaz.R;
import com.example.mangaz.SliderAdapter;
import com.example.mangaz.manga.MangaAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NominationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Nomination> nominationList;
    private IClickItem mIClickItem;
    private static int TYPE_VIEW_1 = 1;
    private static int TYPE_VIEW_2 = 2;

    public interface IClickItem { // interface Sự kiện click
        void onClick(Manga manga);
        void onClickImageButton();
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nominations_home_fragment, parent, false);
                return new NominationViewHolder(view);
            }
            case 2: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_slider, parent, false);
                return new ImageViewHolder(view);
            }
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Nomination nomination = nominationList.get(position);
        if (nomination == null) {
            return;
        }
        if (holder.getItemViewType() == TYPE_VIEW_1){
            NominationViewHolder nominationViewHolder = (NominationViewHolder) holder;
            nominationViewHolder.textViewNominationName.setText(nomination.getNominationName());
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            nominationViewHolder.recyclerViewNominationManga.setLayoutManager(mLinearLayoutManager); // set HORIZONTAL on RecyclerView

            MangaAdapter mangaAdapter = new MangaAdapter(new MangaAdapter.IClickItemListener() {
                @Override
                public void onClickItemManga(Manga manga) {
                    mIClickItem.onClick(manga); // Sự kiện Click
                }
            });
            mangaAdapter.setData(nomination.getMangaList());
            nominationViewHolder.recyclerViewNominationManga.setAdapter(mangaAdapter);
        }else {
            if (holder.getItemViewType() == TYPE_VIEW_2){
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                SliderAdapter sliderAdapter = new SliderAdapter(new SliderAdapter.IClickItemSlider() {
                    @Override
                    public void onClick(Manga manga) {
                        mIClickItem.onClick(manga); // Sự kiện Click
                    }
                });
                sliderAdapter.setData(nomination.getMangaList());
                imageViewHolder.sliderView.setSliderAdapter(sliderAdapter);
                imageViewHolder.sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                imageViewHolder.sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
                imageViewHolder.sliderView.startAutoCycle();
                imageViewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mIClickItem.onClickImageButton();
                    }
                });
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        Nomination nomination = nominationList.get(position);
        switch (nomination.getType()) {
            case 1: {
                return TYPE_VIEW_1;
            }
            case 2: {
                return TYPE_VIEW_2;
            }
        }
        return TYPE_VIEW_1;
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

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private SliderView sliderView;
        private ImageButton imageButton;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            sliderView = itemView.findViewById(R.id.sliderView);
            imageButton = itemView.findViewById(R.id.imageButtonSearchHomeFragment);
        }
    }

}
