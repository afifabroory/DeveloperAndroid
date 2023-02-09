package com.example.docseditor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DocumentsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Documents word);

    @Query("DELETE FROM documents")
    void deleteAll();

    @Query("SELECT * FROM documents ORDER BY id ASC")
    LiveData<List<Documents>> getAllTitle();

    @Query("SELECT * FROM documents WHERE id = :id")
    LiveData<Documents> getDocuments(int id);

    @Query("UPDATE documents SET title = :title, content = :content WHERE id = :id")
    void updateDocument(int id, String title, String content);
}
