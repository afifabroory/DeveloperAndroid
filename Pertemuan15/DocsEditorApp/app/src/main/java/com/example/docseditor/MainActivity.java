package com.example.docseditor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.View;

import com.example.docseditor.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container, DocumentRecyclerFragment.class, null)
                .commit();

        binding.fab.setOnClickListener((View view) -> {
            binding.fab.hide();
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container, DocumentEditorFragment.class, null)
                    .addToBackStack(null)
                    .commit();
        });

        getSupportFragmentManager().setFragmentResultListener(DocumentEditorFragment.EDIT, this, (requestKey, result) -> {
            Fragment documentEditor = new DocumentEditorFragment();
            documentEditor.setArguments(result);

            binding.fab.hide();

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container, documentEditor)
                    .addToBackStack(null)
                    .commit();
        });

        getSupportFragmentManager().setFragmentResultListener(DocumentEditorFragment.CANCEL, this, (requestKey, result) -> {
            binding.fab.show();

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container, DocumentRecyclerFragment.class, null)
                    .commit();
        });
    }
}