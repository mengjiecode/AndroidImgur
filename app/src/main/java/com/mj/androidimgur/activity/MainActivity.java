package com.mj.androidimgur.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mj.androidimgur.R;
import com.mj.androidimgur.adapter.GalleryItemsAdapter;
import com.mj.androidimgur.domain.SimpleGalleryItem;
import com.mj.androidimgur.model.GalleryItem;
import com.mj.androidimgur.model.GalleryResponse;
import com.mj.androidimgur.model.Image;
import com.mj.androidimgur.rest.ApiClient;
import com.mj.androidimgur.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import io.github.kobakei.materialfabspeeddial.FabSpeedDial;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Client ID:
 * <p>
 * 3e9a42907525a21
 * <p>
 * <p>
 * Client secret:
 * <p>
 * a7e68e95f09f113b3d95be29c68c717ae989c4ed
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private GalleryItemsAdapter adapter;
    private List<SimpleGalleryItem> galleryItemList = new ArrayList<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private ProgressBar progressBar;

    private String imageTypePng = "image/png";
    private String imageTypeJpeg = "image/jpeg";

    private String section = "hot";
    private String window = "day";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        initFloatingButton();

        initRecyclerView();

        getGallery();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState()");
    }

    private void initFloatingButton() {
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show());

        final FabSpeedDial fab = findViewById(R.id.fab);
        fab.addOnMenuItemClickListener(new FabSpeedDial.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(FloatingActionButton fab, TextView label, int itemId) {
                Toast.makeText(MainActivity.this, "Click: " + itemId + "label: " + label.getText().toString(), Toast.LENGTH_SHORT).show();
                String text = label.getText().toString().toLowerCase();
                if (text.equalsIgnoreCase("newest")) {
                    section = "top";
                } else if (text.equalsIgnoreCase("popular")) {
                    section = "hot";
                } else if (text.equalsIgnoreCase("day")
                        || text.equalsIgnoreCase("week")
                        || text.equalsIgnoreCase("month")) {
                    window = text;
                }
                getGallery();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new GalleryItemsAdapter(galleryItemList, this.getApplicationContext());

        // set a StaggeredGridLayoutManager with 2 number of columns and vertical orientation
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


        // adding inbuilt divider line
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);

    }

    private void getGallery() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Single<GalleryResponse> results = apiInterface.getGalleryDetails(section, "viral",
                window, 1, true, true, true);
        Single<List<SimpleGalleryItem>> getImages = results
                .flatMap(resp -> Observable.fromIterable(resp.getData())
                        .map(galleryItem -> {
                            SimpleGalleryItem simpleGalleryItem = new SimpleGalleryItem();
                            simpleGalleryItem.setTitle(galleryItem.getTitle());
                            simpleGalleryItem.setAlbum(galleryItem.isAlbum());
                            if (galleryItem.isAlbum()) {
                                simpleGalleryItem.setImagesCount(galleryItem.getImagesCount());
                                if (galleryItem.getImagesCount() > 0 && galleryItem.getImages() != null && galleryItem.getImages().size() > 0) {
                                    simpleGalleryItem.setImages(galleryItem.getImages());
                                    Image image = galleryItem.getImages().get(0);

                                    if (image.getType().equalsIgnoreCase(imageTypeJpeg) || image.getType().equalsIgnoreCase(imageTypePng)) {
                                        if (image.getLink() != null && !image.getLink().isEmpty()) {
                                            simpleGalleryItem.setLink(image.getLink());
                                        }
                                    }
                                }
                            } else {
                                if (galleryItem.getType().equalsIgnoreCase(imageTypeJpeg) || galleryItem.getType().equalsIgnoreCase(imageTypePng)) {
                                    simpleGalleryItem.setLink(galleryItem.getLink());
                                }
                            }

                            return simpleGalleryItem;
                        })
                        .filter(simpleGalleryItem -> simpleGalleryItem != null && simpleGalleryItem.getLink() != null)
                        .toList());

        disposable.add(getImages
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> progressBar.setVisibility(View.VISIBLE))
                .doOnSuccess(s -> progressBar.setVisibility(View.GONE))
                .subscribe(imageItems -> {
                    Log.d(TAG, "lol tommy: " + imageItems.size());
//                    adapter = new GalleryItemsAdapter(imageItems, MainActivity.this.getApplicationContext());
//                    recyclerView.setAdapter(adapter);
                    // notify adapter about data set changes
                    // so that it will render the list with new data
                    adapter.setItemList(imageItems);
                    adapter.notifyDataSetChanged();
                }));

    }


}
