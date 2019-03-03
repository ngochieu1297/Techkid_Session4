package com.example.admin.techkid_session4.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.techkid_session4.R;
import com.example.admin.techkid_session4.activity.MainActivity;
import com.example.admin.techkid_session4.database.MusicTypeModel;
import com.example.admin.techkid_session4.fragment.TopSongFragment;
import com.example.admin.techkid_session4.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicTypesAdapter extends RecyclerView.Adapter<MusicTypesAdapter.MusicTypesViewHolder> {
    List<MusicTypeModel> musicTypeModels = new ArrayList<>();
    Context context;

    public MusicTypesAdapter(List<MusicTypeModel> musicTypeModels, Context context) {
        this.musicTypeModels = musicTypeModels;
        this.context = context;
    }

    //Tao item view
    @Override
    public MusicTypesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_music, parent, false);

        return new MusicTypesViewHolder(itemView);
    }

    //Load data len ietm view
    @Override
    public void onBindViewHolder(@NonNull MusicTypesViewHolder holder, int position) {
        holder.setData(musicTypeModels.get(position));
    }

    @Override
    public int getItemCount() {
        return musicTypeModels.size();
    }

    public class MusicTypesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_music_type)
        ImageView ivMusicType;
        @BindView(R.id.tv_music_type)
        TextView tvMusicType;

        public MusicTypesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        //Load data ntn ?
        public void setData(final MusicTypeModel musicTypeModel) {
            Picasso.get().load(musicTypeModel.imageID).into(ivMusicType);
            tvMusicType.setText(musicTypeModel.name);

            ivMusicType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TopSongFragment topSongFragment = new TopSongFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("music_type_model", musicTypeModel);
                    topSongFragment.setArguments(bundle);

                    Utils.openFragment(((MainActivity)context).getSupportFragmentManager(), R.id.ll_main, topSongFragment);
                }
            });
        }
    }
}
