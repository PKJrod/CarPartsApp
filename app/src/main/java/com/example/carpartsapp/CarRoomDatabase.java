package com.example.carpartsapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Car.class, CarPart.class}, version = 1)
public abstract class CarRoomDatabase extends RoomDatabase{

    public abstract CarDao carDao();

    public abstract CarPartDao carPartDao();

    private static CarRoomDatabase INSTANCE;

    public static synchronized CarRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            CarRoomDatabase.class, "vehicle_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
        }
        return INSTANCE;
    }

    // below line is to create a callback for our room database.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(CarRoomDatabase instance) {
            CarDao carDao = instance.carDao();
            CarPartDao carPartDao = instance.carPartDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
