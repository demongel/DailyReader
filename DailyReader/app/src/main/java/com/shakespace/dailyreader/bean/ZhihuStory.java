package com.shakespace.dailyreader.bean;

import com.shakespace.dailyreader.db.dao.converter.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;

/**
 * Created by shakespace on 2018/3/28.
 */
@Entity
public class ZhihuStory {
    private String title;
    private String ga_prefix;
    private boolean multipic;

    private String date;
    private int type;

    @Id
    private Long id;
    @Convert(converter = StringConverter.class, columnType = String.class)
    private List<String> images;


    @Generated(hash = 721626362)
    public ZhihuStory(String title, String ga_prefix, boolean multipic, String date,
                      int type, Long id, List<String> images) {
        this.title = title;
        this.ga_prefix = ga_prefix;
        this.multipic = multipic;
        this.date = date;
        this.type = type;
        this.id = id;
        this.images = images;
    }

    @Generated(hash = 209309419)
    public ZhihuStory() {
    }

    // id和title相同  即视为同一个  避免重复添加


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZhihuStory story = (ZhihuStory) o;

        if (!title.equals(story.title)) return false;
        return id.equals(story.id);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getMultipic() {
        return this.multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }
}
