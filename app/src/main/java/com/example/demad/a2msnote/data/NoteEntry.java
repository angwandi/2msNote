package com.example.demad.a2msnote.data;

public class NoteEntry {
    private static final String TAG = NoteEntry.class.getSimpleName();
    public final String title;
    public final String description;
    public final int imageView;

    public NoteEntry(String title, String description, int imageView) {
        this.title = title;
        this.description = description;
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageView() {
        return imageView;
    }

}
