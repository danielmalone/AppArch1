package com.finepointstudios.apparch1;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends LifecycleActivity {

    private static final String TAG = "MainActivity";

    Context mContext;
    AppDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mDatabase = Room.databaseBuilder(mContext, AppDatabase.class, "mDatabase-users").build();

        new SaveData().execute();

        new GetData().execute();
    }

    private class SaveData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            User user = new User();
            user.setFirstName("Jake");
            user.setLastName("Blake");
//            user.setUid(50);
//            mDatabase.userDao().insertAll(user);

            return null;
        }
    }

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            List<User> users = mDatabase.userDao().getAll();

            for (int i = 0; i < users.size(); i++) {
                User thisUser = users.get(i);
                Log.d(TAG, "doInBackground: User: " + thisUser.getFirstName() + " " + thisUser.getUid() + " " + thisUser.getLastName());
            }

            return null;
        }
    }
}
