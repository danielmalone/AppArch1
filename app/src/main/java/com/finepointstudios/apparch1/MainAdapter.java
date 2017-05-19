package com.finepointstudios.apparch1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by danielmalone on 5/19/17.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    List<User> mDataset;

    public MainAdapter(List<User> dataset) {
        mDataset = dataset;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        holder.mFirstName.setText(mDataset.get(position).getFirstName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mFirstName;

        public ViewHolder(View itemView) {
            super(itemView);

            mFirstName = (TextView) itemView.findViewById(R.id.first_name);
        }
    }
}
