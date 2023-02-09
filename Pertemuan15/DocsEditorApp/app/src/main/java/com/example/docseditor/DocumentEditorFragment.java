package com.example.docseditor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.docseditor.databinding.FragmentDocumentEditorBinding;

import java.util.List;

public class DocumentEditorFragment extends Fragment {
    private DocumentsRepository documentsRepository;
    private FragmentDocumentEditorBinding binding;

    public final static String EDIT = "com.example.docseditor.EDIT_DOCS";
    public final static String CANCEL = "com.example.docseditor.CANCEL";
    public final static String SAVE = "com.example.docseditor.SAVE";
    private int mId = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        documentsRepository = new DocumentsRepository(null);

        if (getArguments() != null) {
            mId = getArguments().getInt("EDIT");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mId != -1) {
            documentsRepository.getDocument(mId).observe(getViewLifecycleOwner(), new Observer<Documents>() {
                @Override
                public void onChanged(Documents documents) {
                    binding.edittextTitle.setText(documents.getTitle());
                    binding.editextContents.setText(documents.getContent());
                }
            });
        }

        binding.btnCancel.setOnClickListener((v) -> {
            getParentFragmentManager().setFragmentResult(CANCEL, null);
        });

        binding.btnSave.setOnClickListener((v) -> {
            Documents data = new Documents(binding.edittextTitle.getText().toString(),  binding.editextContents.getText().toString());

            if (mId != -1) {
                data.setId(mId);
                documentsRepository.update(data);
            } else  {
                documentsRepository.insert(data);
            }
            getParentFragmentManager().popBackStack();
            Toast.makeText(getContext(), "Data sucessfuly saved!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDocumentEditorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}