package com.example.admin.techkid_session4.utils;

import android.content.Context;
import android.util.Log;

import com.example.admin.techkid_session4.database.TopSongModel;
import com.example.admin.techkid_session4.network.LocationResponse;
import com.example.admin.techkid_session4.network.MusicService;
import com.example.admin.techkid_session4.network.RetrofitInstance;
import com.example.admin.techkid_session4.network.SearchSongResponse;

import hybridmediaplayer.HybridMediaPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicHandle {
    private static final String TAG = "MusicHandle";
    public static void getSearchSong(TopSongModel topSongModel, final Context context) {
        MusicService musicService = RetrofitInstance.getRetrofitInstance().create(MusicService.class);
        musicService.getSearchSong(topSongModel.song + " " + topSongModel.artist).enqueue(new Callback<SearchSongResponse>() {
            @Override
            public void onResponse(Call<SearchSongResponse> call, Response<SearchSongResponse> response) {
                String url = response.body().data.url;
                getLocationSong(url, context);
            }

            @Override
            public void onFailure(Call<SearchSongResponse> call, Throwable t) {

            }
        });
    }

    public static void getLocationSong(String url, final Context context) {
        MusicService musicService = RetrofitInstance.getRetrofitInstance()
                .create(MusicService.class);
        musicService.getLocation(url.split("=")[1]).enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                final HybridMediaPlayer hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
                hybridMediaPlayer.setDataSource(response.body().trackXML.location);
                hybridMediaPlayer.prepare();
                hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(HybridMediaPlayer player) {
                        hybridMediaPlayer.play();
                    }
                });
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {

            }
        });
    }

}
