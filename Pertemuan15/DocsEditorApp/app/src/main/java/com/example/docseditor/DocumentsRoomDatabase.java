package com.example.docseditor;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Documents.class}, version = 1, exportSchema = false)
public abstract class DocumentsRoomDatabase extends RoomDatabase {
    private static DocumentsRoomDatabase INSTANCE;

    public abstract DocumentsDao wordDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    public static DocumentsRoomDatabase getDatabase(final Context contex) {
        if (INSTANCE == null) {
            synchronized (DocumentsRoomDatabase.class) {
                INSTANCE = Room.databaseBuilder(contex.getApplicationContext(), DocumentsRoomDatabase.class,
                        "documents").fallbackToDestructiveMigration().build();
                        //.addCallback(sRoomDatabaseCallback).build();
            }
        }
        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DocumentsDao mDao;
        String[] titles = {"dolphin", "crocodile", "cobra"};
        String[] contents = {"Hello from Dolphin!", "Hello from Crocodile!", "Hello from Cobra"};

        PopulateDbAsync(DocumentsRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();

            for (int i = 0; i <= titles.length - 1; i++) {
                Documents word = new Documents(titles[i], contents[i]);
                mDao.insert(word);
            }
            return null;
        }
    }
}
