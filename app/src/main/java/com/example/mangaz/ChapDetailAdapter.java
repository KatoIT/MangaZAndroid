package com.example.mangaz;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.Model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapDetailAdapter extends RecyclerView.Adapter<ChapDetailAdapter.ChapterDetailViewAdapter>{

    private List<Chapter> listchap;
    //private Chapter chapter;
    private OnClickChapListener clickChapListener;

    public interface OnClickChapListener{
        void OnClickChap(Chapter chapter);
    }

    public ChapDetailAdapter(List<Chapter> listchap, OnClickChapListener clickChapListener) {
        this.listchap = listchap;
        this.clickChapListener = clickChapListener;
    }

    public void setdata(List<Chapter> list){
        this.listchap=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ChapterDetailViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chapdetail,viewGroup,false);
        return new ChapterDetailViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterDetailViewAdapter chapterDetailViewAdapter, int i) {
        final Chapter chapter=listchap.get(i);
        //this.chapter=chapter;
        if(chapter==null){
            return;
        }
        chapterDetailViewAdapter.tvChapname.setText("Chap " +chapter.getChap());
        chapterDetailViewAdapter.tvUpdatetime.setText(chapter.getDateUpdate());

        //bat su kien khi click vao 1 item trong rcvchap
        chapterDetailViewAdapter.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickChapListener.OnClickChap(chapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listchap!=null){
            return listchap.size();
        }
        return 0;
    }

    public class ChapterDetailViewAdapter extends RecyclerView.ViewHolder{

        //private ImageView imgPoster;
        private TextView tvChapname,tvUpdatetime;
        public ChapterDetailViewAdapter(@NonNull View itemView) {
            super(itemView);

            //anh xa
            //imgPoster=(ImageView) itemView.findViewById(R.id.imgposter);
            tvChapname=(TextView) itemView.findViewById(R.id.tvChapterName);
            tvUpdatetime=(TextView) itemView.findViewById(R.id.tvUpdateTime);
        }
    }
}
