package com.learntodroid.wallpaperapptutorial;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class WallpaperViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private ImageView image;

    public WallpaperViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.item_wallpaper_title);
        image = itemView.findViewById(R.id.item_wallpaper_image);
    }

    public void bind(final Wallpaper wallpaper, final WallpaperSelectListener listener) {
        title.setText(wallpaper.getTitle());
        Glide.with(itemView).load(wallpaper.getImageUri()).into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onWallpaperSelect(wallpaper);
            }
        });
    }
}
