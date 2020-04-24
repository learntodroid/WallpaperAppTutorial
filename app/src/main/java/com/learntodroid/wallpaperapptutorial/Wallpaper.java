package com.learntodroid.wallpaperapptutorial;

public class Wallpaper {
    private String title, imageUri;

    public Wallpaper(String title, String imageUri) {
        this.title = title;
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUri() {
        return imageUri;
    }
}
