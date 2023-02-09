package com.example.docseditor;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DocumentsViewModel extends AndroidViewModel {
    private DocumentsRepository mRepository;
    private LiveData<List<Documents>> mAllWords;

    public DocumentsViewModel(Application application) {
        super(application);
        mRepository = new DocumentsRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Documents>> getAllWords() {return mAllWords;}

    public void insert(Documents word) {mRepository.insert(word);}
}
