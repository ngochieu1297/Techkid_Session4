package com.example.admin.techkid_session4.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MusicService {
    @GET("api")
    Call<MusicTypesResponse> getListMusicTypes();
    //api dc goi la endpoint (phan ghep vs base url)

    @GET("https://itunes.apple.com/us/rss/topsongs/limit=30/genre={idMusicType}/explicit=true/json")
    Call<TopSongResponse> getTopSongs(@Path("idMusicType") String idMusicType);

    @GET("http://services.techkids.vn/api/audio")
    Call<SearchSongResponse> getSearchSong(@Query("search_terms") String query);

    @GET("xml")
    Call<LocationResponse> getLocation(@Query("key1") String key);
}
