package com.example.demad.a2msnote.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.res.Resources;

import java.util.Date;
import java.util.List;

@Entity(tableName = "notes")
public class NoteEntry {
    @PrimaryKey(autoGenerate = true)  //auto generated primary id by Room
    private int id;
    private String title;
    private String description;
    private int priority;
    private Date updatedAt;

    @Ignore
    public NoteEntry(String title, String description, int priority, Date updatedAt) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    @Ignore
    public NoteEntry(int id, String title, String description, int priority, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    public NoteEntry(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<NoteEntry> initNoteEntryList(Resources resources) {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
