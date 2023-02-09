package com.example.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private String word;

    public Word(int id, @NonNull String word) {
        this.id = id;
        this.word = word;
    }
    public String getWord() {
        return this.word;
    }
    public int getId() {
        return this.id;
    }
}
