package com.mory.moryblog.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.mory.moryblog.R;
import com.mory.moryblog.adapter.FragmentAdapter;
import com.mory.moryblog.fragment.PhotoFragment;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    private List<String> pic_urls;
    private int position;
    private ViewPager vpPics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        setViews();
        getData();
        init();
    }

    private void setViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        vpPics = findViewById(R.id.vpPics);
    }

    private void init() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < pic_urls.size(); i++) {
            fragments.add(PhotoFragment.newInstance(pic_urls.get(i)));
        }
        vpPics.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
        vpPics.setCurrentItem(position);
    }

    private void getData() {
        Intent i = getIntent();
        pic_urls = i.getStringArrayListExtra("picUrls");
        position = i.getIntExtra("position", 0);
    }
}
