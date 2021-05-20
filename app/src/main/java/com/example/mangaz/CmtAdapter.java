package com.example.mangaz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.Model.Comment;
import com.example.mangaz.Model.User;

import java.util.List;

public class CmtAdapter extends RecyclerView.Adapter<CmtAdapter.CmtViewAdapter> {

    private List<Comment> listcmt;
    public void setdata(List<Comment> list){
        this.listcmt=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CmtViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cmt_item,viewGroup,false);
        return new CmtViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CmtViewAdapter cmtViewAdapter, int i) {
        Comment comment=listcmt.get(i);
        if(comment==null){
            return;
        }
        if(comment.getmUser().getUrlImage().length()<2){
            cmtViewAdapter.imgAvatar.setImageResource(R.drawable.app);
        }else {
            cmtViewAdapter.imgAvatar.setImageResource(Integer.parseInt(comment.getmUser().getUrlImage()));// image
        }
        cmtViewAdapter.tvCUserName.setText(comment.getmUser().getNickname());
        cmtViewAdapter.tvCmtContent.setText(comment.getContentComment());
        cmtViewAdapter.tvDateCmt.setText(comment.getDateComment());
    }

    @Override
    public int getItemCount() {
        if(listcmt!=null){
            return listcmt.size();
        }
        return 0;
    }

    public class CmtViewAdapter extends RecyclerView.ViewHolder{

        private ImageView imgAvatar;
        private TextView tvCUserName,tvCmtContent,tvDateCmt;
        public CmtViewAdapter(@NonNull View itemView) {
            super(itemView);
            //anh xa
            imgAvatar=(ImageView) itemView.findViewById(R.id.Cmt_Avartar);
            tvCUserName=(TextView) itemView.findViewById(R.id.tvCUserName);
            tvCmtContent=(TextView) itemView.findViewById(R.id.CmtContent);
            tvDateCmt=(TextView) itemView.findViewById(R.id.tvDateCmt);
        }
    }
}
