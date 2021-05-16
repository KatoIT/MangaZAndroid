/*
 * Coder: Nguyen Van An
 * Date: 11-5-2021
 * Content: Xử lý hiển thị list manga (RecyclerView)
 *
 *
 *
 * */
package com.example.mangaz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.login.LoginActivity;
import com.example.mangaz.manga.Manga;
import com.example.mangaz.nomination.Nomination;
import com.example.mangaz.nomination.NominationAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewHomeFragment;
    private NominationAdapter nominationAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // ánh xạ
        recyclerViewHomeFragment = view.findViewById(R.id.recyclerViewHomeFragment);
        nominationAdapter = new NominationAdapter(getActivity(), new NominationAdapter.IClickItem() {
            @Override
            public void onClick(Manga manga) {
                Toast.makeText(getActivity(), manga.getMangaName(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);

            }

            @Override
            public void onClickImageButton() {
//                Toast.makeText(getActivity(), "Search", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }

        });
        // set recyclerViewHomeFragment
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewHomeFragment.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerViewHomeFragment.addItemDecoration(itemDecoration);
        nominationAdapter.setData(getListNomination());
        recyclerViewHomeFragment.setAdapter(nominationAdapter);

        return view;

    }

    private List<Nomination> getListNomination() { // truyền dữ liệu vào RecyclerView
        List<Nomination> listNominations = new ArrayList<>();
        //
        List<Manga> mangaList = new ArrayList<>();
        mangaList.add(new Manga("Dục hỏa độc nữ", "" + R.drawable.img_duc_hoa_doc_nu));
        mangaList.add(new Manga("Người tôi yêu là chàng trai nào", "" + R.drawable.img_nguoi_toi_yeu_la_chang_trai_nao));
        mangaList.add(new Manga("Chử thiên ký", "" + R.drawable.img_chu_thien_ky));
        mangaList.add(new Manga("Ta lượm được thuộc tính ở mạt thế", "" + R.drawable.img_ta_luom_duoc_thuoc_tinh_o_mat_the));
        mangaList.add(new Manga("Vương gia què", "" + R.drawable.img_vuong_gia_que));
        //
        List<Manga> mangaList1 = new ArrayList<>();
        mangaList1.add(new Manga("Người tôi yêu là chàng trai nào", "" + R.drawable.img_nguoi_toi_yeu_la_chang_trai_nao));
        mangaList1.add(new Manga("Chử thiên ký", "" + R.drawable.img_chu_thien_ky));
        mangaList1.add(new Manga("Ta lượm được thuộc tính ở mạt thế", "" + R.drawable.img_ta_luom_duoc_thuoc_tinh_o_mat_the));
        mangaList1.add(new Manga("Dục hỏa độc nữ", "" + R.drawable.img_duc_hoa_doc_nu));
        mangaList1.add(new Manga("Vương gia què", "" + R.drawable.img_vuong_gia_que));
        //
        List<Manga> mangaList2 = new ArrayList<>();
        mangaList2.add(new Manga("Chử thiên ký", "" + R.drawable.img_chu_thien_ky));
        mangaList2.add(new Manga("Ta lượm được thuộc tính ở mạt thế", "" + R.drawable.img_ta_luom_duoc_thuoc_tinh_o_mat_the));
        mangaList2.add(new Manga("Vương gia què", "" + R.drawable.img_vuong_gia_que));
        mangaList2.add(new Manga("Dục hỏa độc nữ", "" + R.drawable.img_duc_hoa_doc_nu));
        mangaList2.add(new Manga("Người tôi yêu là chàng trai nào", "" + R.drawable.img_nguoi_toi_yeu_la_chang_trai_nao));
        //
        List<Manga> mangaList3 = new ArrayList<>();

        mangaList3.add(new Manga("Ta lượm được thuộc tính ở mạt thế", "" + R.drawable.img_ta_luom_duoc_thuoc_tinh_o_mat_the));
        mangaList3.add(new Manga("Vương gia què", "" + R.drawable.img_vuong_gia_que));
        mangaList3.add(new Manga("Người tôi yêu là chàng trai nào", "" + R.drawable.img_nguoi_toi_yeu_la_chang_trai_nao));
        mangaList3.add(new Manga("Dục hỏa độc nữ", "" + R.drawable.img_duc_hoa_doc_nu));
        mangaList3.add(new Manga("Chử thiên ký", "" + R.drawable.img_chu_thien_ky));
        //
        List<Manga> mangaList4 = new ArrayList<>();

        mangaList4.add(new Manga("Vương gia què", "" + R.drawable.img_vuong_gia_que));
        mangaList4.add(new Manga("Người tôi yêu là chàng trai nào", "" + R.drawable.img_nguoi_toi_yeu_la_chang_trai_nao));
        mangaList4.add(new Manga("Chử thiên ký", "" + R.drawable.img_chu_thien_ky));
        mangaList4.add(new Manga("Ta lượm được thuộc tính ở mạt thế", "" + R.drawable.img_ta_luom_duoc_thuoc_tinh_o_mat_the));
        mangaList4.add(new Manga("Dục hỏa độc nữ", "" + R.drawable.img_duc_hoa_doc_nu));


        listNominations.add(new Nomination(mangaList, "Mới cập nhật", 2));
        listNominations.add(new Nomination(mangaList1, "Mới cập nhật", 1));
        listNominations.add(new Nomination(mangaList2, "Hot", 1));
        listNominations.add(new Nomination(mangaList3, "Top tuần", 1));
        listNominations.add(new Nomination(mangaList4, "Top tháng", 1));
        listNominations.add(new Nomination(mangaList, "Top lượt xem", 1));
        return listNominations;
    }


}