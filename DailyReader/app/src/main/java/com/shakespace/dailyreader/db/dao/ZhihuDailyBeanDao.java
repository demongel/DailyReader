package com.shakespace.dailyreader.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.shakespace.dailyreader.bean.ZhihuDailyBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ZHIHU_DAILY_BEAN".
*/
public class ZhihuDailyBeanDao extends AbstractDao<ZhihuDailyBean, Long> {

    public static final String TABLENAME = "ZHIHU_DAILY_BEAN";

    /**
     * Properties of entity ZhihuDailyBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Date = new Property(1, String.class, "date", false, "DATE");
    }

    private DaoSession daoSession;


    public ZhihuDailyBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ZhihuDailyBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ZHIHU_DAILY_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"DATE\" TEXT);"); // 1: date
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_ZHIHU_DAILY_BEAN_DATE ON \"ZHIHU_DAILY_BEAN\"" +
                " (\"DATE\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ZHIHU_DAILY_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ZhihuDailyBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(2, date);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ZhihuDailyBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(2, date);
        }
    }

    @Override
    protected final void attachEntity(ZhihuDailyBean entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ZhihuDailyBean readEntity(Cursor cursor, int offset) {
        ZhihuDailyBean entity = new ZhihuDailyBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // date
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ZhihuDailyBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDate(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ZhihuDailyBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ZhihuDailyBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ZhihuDailyBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
