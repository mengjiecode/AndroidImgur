package com.mj.androidimgur.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mj.androidimgur.R;
import com.mj.androidimgur.activity.DetailActivity;
import com.mj.androidimgur.domain.SimpleGalleryItem;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * Created by mengjie.huang on 30/8/2018.
 */

public class GalleryItemsAdapter extends RecyclerView.Adapter<GalleryItemsAdapter.GalleryItemHolder> {
    private static final String TAG = GalleryItemsAdapter.class.getSimpleName();
    private List<SimpleGalleryItem> itemList;
    private Context context;

    public GalleryItemsAdapter(List<SimpleGalleryItem> galleryItemList, Context context) {
        super();
        Log.d(TAG, "item = " + galleryItemList.size());
        this.itemList = galleryItemList;
        this.context = context;
    }

    public List<SimpleGalleryItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SimpleGalleryItem> itemList) {
        this.itemList.clear();
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public GalleryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gallery,
                parent, false);
        return new GalleryItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryItemHolder holder, int position) {
        if (itemList != null && itemList.size() > 0) {
            SimpleGalleryItem item = itemList.get(position);
            if (item != null) {
                String url = item.getLink();
                Log.d(TAG, "onBindViewHolder(), image.getLink() == " + url);

                Glide.with(context)
                        .load(url)
                        .thumbnail(1.0f)
                        .into(holder.imageView);

                String title = item.getTitle();
                holder.textView.setText(title);

                // implement setOnClickListener event on item view.
                holder.itemView.setOnClickListener(view -> {
                    Log.d(TAG, "onBindViewHolder(), onClick(), position == " + position);
                    // open another activity on item click
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("item", item);
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent); // start Intent
                });

                if (item.isAlbum() && item.getImages() != null && item.getImages().size() > 1) {
                    holder.txtImageCount.setText(item.getImages().size() + "");
                    holder.txtImageCount.setVisibility(View.VISIBLE);
                    holder.imgMultiple.setVisibility(View.VISIBLE);
                } else {
                    holder.txtImageCount.setVisibility(View.INVISIBLE);
                    holder.imgMultiple.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (itemList == null) {
            return 0;
        }
        return itemList.size();
    }

    public static class GalleryItemHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView txtImageCount;
        ImageView imgMultiple;

        public GalleryItemHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            txtImageCount = itemView.findViewById(R.id.txtImageCount);
            imgMultiple = itemView.findViewById(R.id.imgMultiple);
        }
    }
}
