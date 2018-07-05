package com.shakespace.dailyreader.db.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.shakespace.dailyreader.bean.ZhihuDailyBean;
import com.shakespace.dailyreader.bean.ZhihuStory;

import com.shakespace.dailyreader.db.dao.ZhihuDailyBeanDao;
import com.shakespace.dailyreader.db.dao.ZhihuStoryDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig zhihuDailyBeanDaoConfig;
    private final DaoConfig zhihuStoryDaoConfig;

    private final ZhihuDailyBeanDao zhihuDailyBeanDao;
    private final ZhihuStoryDao zhihuStoryDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        zhihuDailyBeanDaoConfig = daoConfigMap.get(ZhihuDailyBeanDao.class).clone();
        zhihuDailyBeanDaoConfig.initIdentityScope(type);

        zhihuStoryDaoConfig = daoConfigMap.get(ZhihuStoryDao.class).clone();
        zhihuStoryDaoConfig.initIdentityScope(type);

        zhihuDailyBeanDao = new ZhihuDailyBeanDao(zhihuDailyBeanDaoConfig, this);
        zhihuStoryDao = new ZhihuStoryDao(zhihuStoryDaoConfig, this);

        registerDao(ZhihuDailyBean.class, zhihuDailyBeanDao);
        registerDao(ZhihuStory.class, zhihuStoryDao);
    }
    
    public void clear() {
        zhihuDailyBeanDaoConfig.clearIdentityScope();
        zhihuStoryDaoConfig.clearIdentityScope();
    }

    public ZhihuDailyBeanDao getZhihuDailyBeanDao() {
        return zhihuDailyBeanDao;
    }

    public ZhihuStoryDao getZhihuStoryDao() {
        return zhihuStoryDao;
    }

}