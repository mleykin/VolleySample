package com.example.mleykin.volleysample;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class JokesLayoutAdapter extends RecyclerView.Adapter<JokesLayoutAdapter.MyViewHolder> {
    private ArrayList<Pair<String, String>> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView jokeView;
        public MyViewHolder(TextView v) {
            super(v);
            jokeView = v;
        }
    }

    public JokesLayoutAdapter(ArrayList<Pair<String, String>> Dataset) {
        mDataset = Dataset;
    }

    @Override
    public JokesLayoutAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_joke_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, String> item = mDataset.get(position);
        holder.jokeView.setText(item.first + " " + item.second);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void add(int position, Pair<String, String> item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }
}
