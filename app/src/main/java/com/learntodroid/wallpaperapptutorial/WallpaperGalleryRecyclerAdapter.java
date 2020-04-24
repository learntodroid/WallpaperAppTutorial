package com.learntodroid.wallpaperapptutorial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WallpaperGalleryRecyclerAdapter extends RecyclerView.Adapter<WallpaperViewHolder> {
    private WallpaperSelectListener listener;
    private List<Wallpaper> wallpapers;

    public WallpaperGalleryRecyclerAdapter(WallpaperSelectListener listener) {
        this.listener = listener;
        this.wallpapers = new ArrayList<Wallpaper>();
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallpaper, parent, false);
        return new WallpaperViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
        Wallpaper wallpaper = wallpapers.get(position);
        holder.bind(wallpaper, listener);
    }

    @Override
    public int getItemCount() {
        return wallpapers.size();
    }

    public void setWallpapers(List<Wallpaper> wallpapers) {
        this.wallpapers = wallpapers;
        notifyDataSetChanged();
    }
}
