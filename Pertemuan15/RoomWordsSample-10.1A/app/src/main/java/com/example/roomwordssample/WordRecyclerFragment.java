package com.example.roomwordssample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordssample.databinding.FragmentWordRecyclerBinding;

import java.util.List;

public class WordRecyclerFragment extends Fragment {

    private FragmentWordRecyclerBinding binding;
    private WordViewModel mWordViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentWordRecyclerBinding.inflate(inflater, container, false);

        getParentFragmentManager().setFragmentResultListener(NewWordFragment.RESULT_OK, this, (requestKey, result) -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, this, null)
                    .commit();
            Word word = new Word(result.getString(NewWordFragment.EXTRA_REPLY));
            mWordViewModel.insert(word);
        });

        getParentFragmentManager().setFragmentResultListener(NewWordFragment.RESULT_CANCELED, this, (requestKey, result) -> {
            Toast.makeText(getContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        });

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = binding.recyclerview;
        final WordListAdapter adapter = new WordListAdapter(getContext().getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));

        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(getViewLifecycleOwner(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setWords(words);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}