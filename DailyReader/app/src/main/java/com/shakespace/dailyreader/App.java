package com.shakespace.dailyreader;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.shakespace.dailyreader.db.dao.DaoMaster;
import com.shakespace.dailyreader.db.dao.DaoSession;

/**
 * Created by shakespace on 2018/3/29.
 */

public class App extends Application {


    private static DaoSession sDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper mOpenHelper = new DaoMaster.DevOpenHelper(this, "daily_reader_db", null);
        SQLiteDatabase database = mOpenHelper.getWritableDatabase();
        DaoMaster master = new DaoMaster(database);
        sDaoSession = master.newSession();
    }

    public static DaoSession  getDaoSession(){
        return sDaoSession;
    }


}
