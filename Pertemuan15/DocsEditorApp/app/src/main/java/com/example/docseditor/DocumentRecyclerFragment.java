package com.example.docseditor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docseditor.databinding.FragmentWordRecyclerBinding;

import java.util.List;

public class DocumentRecyclerFragment extends Fragment implements DocumentListAdapter.OnDocItemClickListener {

    private FragmentWordRecyclerBinding binding;
    private DocumentsViewModel mDocumentsViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentWordRecyclerBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = binding.recyclerview;
        final DocumentListAdapter adapter = new DocumentListAdapter(getContext().getApplicationContext());

        adapter.setDocItemClickListener(this::onDocsClick);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));

        mDocumentsViewModel = new ViewModelProvider(this).get(DocumentsViewModel.class);
        mDocumentsViewModel.getAllWords().observe(getViewLifecycleOwner(), new Observer<List<Documents>>() {
            @Override
            public void onChanged(List<Documents> words) {
                adapter.setWords(words);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDocsClick(int id) {
        Bundle data = new Bundle();
        data.putInt("EDIT", id);
        getParentFragmentManager().setFragmentResult(DocumentEditorFragment.EDIT, data);
    }
}