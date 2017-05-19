package com.finepointstudios.apparch1;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

public class MainActivity extends LifecycleActivity {

    private static final String TAG = "MainActivity";

    Context mContext;
    AppDatabase mDatabase;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    List<User> mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mDatabase = Room.databaseBuilder(mContext, AppDatabase.class, "mDatabase-mDataset").build();

        new SaveData().execute();

        new GetData().execute();
    }

    private void loadRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new MainAdapter(mDataset);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class SaveData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            User user = new User();
            user.setFirstName("Jake");
            user.setLastName("Blake");
            mDatabase.userDao().insertAll(user);

            return null;
        }
    }

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            mDataset = mDatabase.userDao().getAll();

            for (int i = 0; i < mDataset.size(); i++) {
                User thisUser = mDataset.get(i);
                Log.d(TAG, "doInBackground: User: " + thisUser.getFirstName() + " " + thisUser.getUid() + " " + thisUser.getLastName());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadRecyclerView();
        }
    }


}
