package com.example.admin.techkid_session4.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.techkid_session4.R;
import com.example.admin.techkid_session4.adapter.MusicTypesAdapter;
import com.example.admin.techkid_session4.database.MusicTypeModel;
import com.example.admin.techkid_session4.network.MusicService;
import com.example.admin.techkid_session4.network.MusicTypesResponse;
import com.example.admin.techkid_session4.network.RetrofitInstance;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicTypesFragment extends Fragment {
    private static final String TAG = "MusicTypesFragment";

    @BindView(R.id.rv_music_types)
    RecyclerView rvMusicTypes;
    Unbinder unbinder;

    MusicTypesAdapter musicTypesAdapter;
    List<MusicTypeModel> musicTypeModels = new ArrayList<>();
    Context context;
    @BindView(R.id.av_loadin)
    AVLoadingIndicatorView avLoadin;

    public MusicTypesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_types, container, false);
        unbinder = ButterKnife.bind(this, view);

        musicTypesAdapter = new MusicTypesAdapter(musicTypeModels, getContext());
        rvMusicTypes.setAdapter(musicTypesAdapter);

        //Neu khong co dong nay se khong hien recycler view
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,
                2, GridLayoutManager.VERTICAL,
                false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 0 ? 2 : 1;
            }
        });
        rvMusicTypes.setLayoutManager(gridLayoutManager);

        //Lay context cua Activity chua no
        context = getContext();

        loadData();

        return view;
    }

    private void loadData() {
        MusicService musicService = RetrofitInstance.getRetrofitInstance().create(MusicService.class);
        musicService.getListMusicTypes().enqueue(new Callback<MusicTypesResponse>() {
            @Override
            public void onResponse(Call<MusicTypesResponse> call, Response<MusicTypesResponse> response) {
                avLoadin.hide();
                List<MusicTypesResponse.MusicTypeJSON> musicTypeJSONList = response.body().subgenres;
                for (MusicTypesResponse.MusicTypeJSON musicTypeJSON : musicTypeJSONList) {
                    MusicTypeModel musicTypeModel = new MusicTypeModel(musicTypeJSON.id,
                            musicTypeJSON.translation_key,
                            context.getResources().getIdentifier(
                                    "genre_x2_" + musicTypeJSON.id,
                                    "raw", context.getPackageName()
                            )
                    );
                    musicTypeModels.add(musicTypeModel);
                }
                //Ham quan trong khi thay doi du lieu
                musicTypesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MusicTypesResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
