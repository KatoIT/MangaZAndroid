package com.example.mangaz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mangaz.Model.Manga;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {
    private List<Manga> mangaList;
    private IClickItemSlider mIClickItemSlider;

    public interface IClickItemSlider { // interface Sự kiện click
        void onClick(Manga manga);
    }

    public SliderAdapter(IClickItemSlider mIClickItemSlider) {
        this.mIClickItemSlider = mIClickItemSlider;
    }

    public void setData(List<Manga> mangaList) { // update data SliderView
        this.mangaList = mangaList;
        notifyDataSetChanged();
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_fragment_slider, null);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        Manga manga = mangaList.get(position);
        if (manga == null) {
            return;
        }
        if (manga.getUrlImage().length()<2){
            viewHolder.imageView.setImageBitmap(manga.getAvatar());
        }else {
            viewHolder.imageView.setImageResource(Integer.parseInt(manga.getUrlImage()));
        }
        viewHolder.textView.setText(manga.getMangaName());
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickItemSlider.onClick(manga);
            }
        });
    }

    @Override
    public int getCount() {
        if (mangaList != null) {
            return mangaList.size();
        }
        return 0;
    }

    public class SliderViewHolder extends SliderViewAdapter.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private RelativeLayout relativeLayout;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewMangaAvartarItemSlider);
            textView = itemView.findViewById(R.id.textViewMangaNameItemSlider);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutItemSlider);
        }
    }
}
