package com.example.docseditor;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;

public class DocumentsRepository {
    private DocumentsDao mDocumentsDao;
    private LiveData<List<Documents>> mAllTitle;

    DocumentsRepository(Application application) {
        DocumentsRoomDatabase db = DocumentsRoomDatabase.getDatabase(application);
        mDocumentsDao = db.wordDao();
        mAllTitle = mDocumentsDao.getAllTitle();
    }

    LiveData<List<Documents>> getAllWords() {
        return mAllTitle;
    }

    LiveData<Documents> getDocument(int id) {return mDocumentsDao.getDocuments(id); }

    public void insert(Documents word) {
        new insertAsyncTask(mDocumentsDao).execute(word);
    }
    public void update(Documents word) {
        new updateAsyncTask(mDocumentsDao).execute(word);
    }

    private static class updateAsyncTask extends  AsyncTask<Documents, Void, Void> {
        private DocumentsDao mAsyncTaskDao;

        updateAsyncTask(DocumentsDao dao) {mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(Documents... params) {
            mAsyncTaskDao.updateDocument(params[0].getId(), params[0].getTitle(), params[0].getContent());
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<Documents, Void, Void> {

        private DocumentsDao mAsyncTaskDao;

        insertAsyncTask(DocumentsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Documents... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
