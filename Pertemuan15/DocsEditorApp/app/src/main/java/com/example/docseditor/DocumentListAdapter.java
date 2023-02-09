package com.example.docseditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DocumentListAdapter extends RecyclerView.Adapter<DocumentListAdapter.WordViewHolder> {
    private final LayoutInflater mInflater;
    private List<Documents> mWords;
    private OnDocItemClickListener mListener;


    public interface OnDocItemClickListener {
        void onDocsClick(int id);
    }

    DocumentListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    public void setDocItemClickListener(OnDocItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Documents current = mWords.get(position);
            holder.wordItemView.setText(current.getTitle());
            holder.itemView.setOnClickListener((view -> {
                mListener.onDocsClick(mWords.get(position).getId());
            }));
        } else {
            holder.wordItemView.setText("No Documents");
        }
    }

    void setWords(List<Documents> words){
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
