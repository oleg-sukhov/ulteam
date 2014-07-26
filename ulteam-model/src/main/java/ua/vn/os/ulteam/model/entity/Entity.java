package ua.vn.os.ulteam.model.entity;

import java.io.Serializable;

/**
 * @author os
 */
public abstract class Entity implements Serializable {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
