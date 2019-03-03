package com.example.admin.techkid_session4.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.admin.techkid_session4.R;
import com.example.admin.techkid_session4.adapter.ViewPagerAdapter;
import com.example.admin.techkid_session4.database.TopSongModel;
import com.example.admin.techkid_session4.events.OnClickTopSong;
import com.example.admin.techkid_session4.utils.MusicHandle;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.iv_top_song)
    ImageView ivTopSong;
    @BindView(R.id.fb_play)
    FloatingActionButton fbPlay;
    @BindView(R.id.tv_song)
    TextView tvSong;
    @BindView(R.id.tv_artist)
    TextView tvArtist;
    @BindView(R.id.sb_mini)
    SeekBar sbMini;
    @BindView(R.id.rl_mini)
    RelativeLayout rlMini;
    @BindView(R.id.ll_main)
    RelativeLayout llMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        tablayout.getTabAt(0).getIcon().setAlpha(255);
        tablayout.getTabAt(1).getIcon().setAlpha(100);
        tablayout.getTabAt(2).getIcon().setAlpha(100);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(viewPagerAdapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

    }

    @Subscribe
    public void onReceiveTopSong(OnClickTopSong onClickTopSong) {
        TopSongModel topSongModel = onClickTopSong.topSongModel;

        rlMini.setVisibility(View.VISIBLE);
        tvSong.setText(topSongModel.song);
        tvArtist.setText(topSongModel.artist);
        Picasso.get().load(topSongModel.image).transform(new CropCircleTransformation()).into(ivTopSong);

        MusicHandle.getSearchSong(topSongModel, this);
    }

    @OnClick(R.id.fb_play)
    public void onViewClicked() {

    }
}
