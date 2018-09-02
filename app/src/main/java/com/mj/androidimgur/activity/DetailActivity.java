package com.mj.androidimgur.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.mj.androidimgur.R;
import com.mj.androidimgur.adapter.ImageItemsAdapter;
import com.mj.androidimgur.domain.SimpleGalleryItem;
import com.mj.androidimgur.model.Image;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private ImageItemsAdapter adapter;
    private SimpleGalleryItem simpleGalleryItem;
    private List<Image> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");

        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String title = getIntent().getStringExtra("title");

        Log.d(TAG, "title == " + title);

        setTitle(title);

        simpleGalleryItem = getIntent().getParcelableExtra("item");
        if (simpleGalleryItem.isAlbum()) {
            Log.d(TAG, "onCreate(), imageList.size() ");
            imageList = simpleGalleryItem.getImages();
        } else {
            Image image = new Image();
            image.setTitle(simpleGalleryItem.getTitle());
            image.setLink(simpleGalleryItem.getLink());
            imageList.add(image);
        }

        initRecyclerView();

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new ImageItemsAdapter(imageList, this.getApplicationContext());

        // vertical RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
