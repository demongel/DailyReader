package com.shakespace.dailyreader.bean;

import com.shakespace.dailyreader.db.dao.DaoSession;
import com.shakespace.dailyreader.db.dao.ZhihuDailyBeanDao;
import com.shakespace.dailyreader.db.dao.ZhihuStoryDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by shakespace on 2018/3/26.
 */
@Entity
public class ZhihuDailyBean {


    @Id(autoincrement = true)
    private Long id;
    /**
     * date : 20170619
     * stories : [{"title":"爸爸的花儿落了，我也不再是小孩子了","ga_prefix":"061921","images":["https://pic4.zhimg.com/v2-c318b05a8153b78b57b6433b968cb2c7.jpg"],"multipic":true,"type":0,"id":9482840},{"images":["https://pic1.zhimg.com/v2-0414b01d763ed9cbe820f3cf650edb30.jpg"],"type":0,"id":9483034,"ga_prefix":"061920","title":"同样的乐谱，为什么演奏家们弹奏的风格不一样？"},{"images":["https://pic2.zhimg.com/v2-f21d8a5033f041cf0fbb8f19afbc5455.jpg"],"type":0,"id":9482756,"ga_prefix":"061919","title":"扭一扭、再扭一扭\u2026\u2026小小的扭蛋为什么让人上瘾？"},{"images":["https://pic4.zhimg.com/v2-f3897a37df04d22706b53d6d9d55ead7.jpg"],"type":0,"id":9483421,"ga_prefix":"061918","title":"虚伪的爱情、虚伪的社会、还有虚伪的美国梦"},{"images":["https://pic2.zhimg.com/v2-1e081be9e1ffcd424303c7c5d0879a51.jpg"],"type":0,"id":9471474,"ga_prefix":"061917","title":"以唐代的技术水平，老奶奶把铁杵磨成针可能吗？"},{"images":["https://pic3.zhimg.com/v2-dfb7def8057f4974dee3fa8ca2a280e2.jpg"],"type":0,"id":9480946,"ga_prefix":"061916","title":"小说和电影里经常出现的「朗姆酒」，究竟是什么味儿？"},{"images":["https://pic4.zhimg.com/v2-9be3a4d7668a3b0fd67483ac14fb3027.jpg"],"type":0,"id":9483023,"ga_prefix":"061915","title":"绝对不会找我爸这样的男人，因为不想变成第二个我妈"},{"images":["https://pic4.zhimg.com/v2-3fd53090df76771d20b02c79d0c4d203.jpg"],"type":0,"id":9482130,"ga_prefix":"061914","title":"聚光灯好了麦克风也好了，这位细菌，请说出你的故事"},{"images":["https://pic4.zhimg.com/v2-a8ecc5f9e583bbbcf12cbcbd8f260fd7.jpg"],"type":0,"id":9482106,"ga_prefix":"061913","title":"137 亿美元买了家高端有机食品超市，亚马逊这是要干嘛？"},{"images":["https://pic2.zhimg.com/v2-7b595ce0d4de3552e9761f4051b3b9ed.jpg"],"type":0,"id":9481812,"ga_prefix":"061912","title":"大误 · 偏偏不爱你"},{"images":["https://pic1.zhimg.com/v2-96e50aabc5a9e0b8a876971b78d2ba64.jpg"],"type":0,"id":9481787,"ga_prefix":"061911","title":"为什么很多人坚信「富贵险中求」？"},{"images":["https://pic1.zhimg.com/v2-8149914976e539c9a5e621b64bbc6bf8.jpg"],"type":0,"id":9432905,"ga_prefix":"061910","title":"刚创业的小公司，怎样记好账？"},{"images":["https://pic3.zhimg.com/v2-3fe79f7ca5294221962acf82ec17ace2.jpg"],"type":0,"id":9480955,"ga_prefix":"061909","title":"昨天：明天我绝对不迟到\r\n今天：对不起老板，今天我\u2026\u2026"},{"images":["https://pic1.zhimg.com/v2-1b4676136093254a693a52b0fe468074.jpg"],"type":0,"id":9482404,"ga_prefix":"061908","title":"说海豚不用嘴呼吸是没错，只是没想到「还有这种操作？」"},{"images":["https://pic1.zhimg.com/v2-2e3bcb3e0ff89bb7677c704af1899ca8.jpg"],"type":0,"id":9481378,"ga_prefix":"061907","title":"有车有房月入四万五，才算刚刚摸到「中产阶级」的边？"},{"images":["https://pic3.zhimg.com/v2-0d6dfb4ab75812eeb0acbfe172f27be6.jpg"],"type":0,"id":9482128,"ga_prefix":"061907","title":"构思了一个惊天动地的故事，却不知道怎么写下它"},{"images":["https://pic3.zhimg.com/v2-f10d7a54cf69b6405fa040a83585c966.jpg"],"type":0,"id":9482312,"ga_prefix":"061907","title":"只要保持微笑，生活就\u2026\u2026对不起啊，生活不会因此变好"},{"images":["https://pic1.zhimg.com/v2-e69efdbd92357a5a9e17fb1dd53663c4.jpg"],"type":0,"id":9482317,"ga_prefix":"061906","title":"瞎扯 · 如何正确地吐槽"}]
     */
    // 唯一关键属性   不用再设置id
    @Index(unique = true)
    private String date;
    /**
     * title : 爸爸的花儿落了，我也不再是小孩子了
     * ga_prefix : 061921
     * images : ["https://pic4.zhimg.com/v2-c318b05a8153b78b57b6433b968cb2c7.jpg"]
     * multipic : true
     * type : 0
     * id : 9482840
     */

    @ToMany(referencedJoinProperty = "date")
    private List<ZhihuStory> stories;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 614656774)
    private transient ZhihuDailyBeanDao myDao;

    @Generated(hash = 1723815801)
    public ZhihuDailyBean() {
    }

    @Generated(hash = 247740174)
    public ZhihuDailyBean(Long id, String date) {
        this.id = id;
        this.date = date;
    }
    @Keep
    @Generated
    public ZhihuDailyBean(String date, List<ZhihuStory> stories) {
        this.stories = stories;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Keep
    @Generated(hash = 1960734871)
    public List<ZhihuStory> getStories() {
        if (stories == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ZhihuStoryDao targetDao = daoSession.getZhihuStoryDao();
            List<ZhihuStory> storiesNew = targetDao._queryZhihuDailyBean_Stories(date);
            synchronized (this) {
                if (stories == null) {
                    stories = storiesNew;
                }
            }
        }
        return stories;
    }

    public void setStories(List<ZhihuStory> stories) {
        this.stories = stories;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 2006863238)
    public synchronized void resetStories() {
        stories = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1601730126)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getZhihuDailyBeanDao() : null;
    }


}
