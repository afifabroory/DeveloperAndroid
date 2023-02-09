package com.example.roomwordssample;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.roomwordssample.databinding.FragmentNewWordBinding;

public class NewWordFragment extends Fragment {
    private FragmentNewWordBinding binding;

    public static final String EXTRA_REPLY =
            "com.example.roomwordssample.REPLY";
    public static final String RESULT_CANCELED =
            "com.example.roomwordssample.RESULT_CANCELED";
    public static final String RESULT_OK =
            "com.example.roomwordssample.RESULT_OK";
    private EditText mEditWordView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNewWordBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditWordView = binding.editWord;
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Bundle replyIntent = new Bundle();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    getParentFragmentManager().setFragmentResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putString(EXTRA_REPLY, word);
                    getParentFragmentManager().setFragmentResult(RESULT_OK, replyIntent);
                }
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, WordRecyclerFragment.class, null)
                        .commit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}