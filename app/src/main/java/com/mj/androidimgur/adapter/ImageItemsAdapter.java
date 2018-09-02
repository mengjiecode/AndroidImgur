package com.mj.androidimgur.adapter;

import android.content.Context;
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
import com.mj.androidimgur.model.Image;

import java.util.List;

public class ImageItemsAdapter extends RecyclerView.Adapter<ImageItemsAdapter.ImageItemHolder> {

    private static final String TAG = ImageItemsAdapter.class.getSimpleName();
    private List<Image> imageList;
    private Context context;

    public ImageItemsAdapter(List<Image> imageList, Context context) {
        super();
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageItemsAdapter.ImageItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image,
                parent, false);
        return new ImageItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageItemsAdapter.ImageItemHolder holder, int position) {
        if (imageList != null && imageList.size() > 0) {
            Log.d(TAG, "onBindViewHolder(), position == " + position);
            String title = (String) imageList.get(position).getTitle();
            Log.d(TAG, "onBindViewHolder(), title == " + title);
            if (title != null) {
                holder.txtTitle.setText(title);
                holder.txtTitle.setVisibility(View.VISIBLE);
            } else {
                holder.txtTitle.setVisibility(View.INVISIBLE);
            }

            Glide.with(context)
                    .load(imageList.get(position).getLink())
                    .into(holder.imageView);

            String description = imageList.get(position).getDescription();
            Log.d(TAG, "onBindViewHolder(), description == " + description);
            if (description != null) {
                holder.txtDescription.setText(description);
                holder.txtDescription.setVisibility(View.VISIBLE);
            } else {
                holder.txtDescription.setVisibility(View.INVISIBLE);
            }

        }
    }

    @Override
    public int getItemCount() {
        if (imageList == null) {
            return 0;
        }
        return imageList.size();
    }

    public static class ImageItemHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView imageView;
        TextView txtDescription;

        public ImageItemHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textViewTitle);
            imageView = itemView.findViewById(R.id.imageView);
            txtDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
