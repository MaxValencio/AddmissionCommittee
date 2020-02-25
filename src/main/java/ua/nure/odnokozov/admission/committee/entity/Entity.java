package ua.nure.odnokozov.admission.committee.entity;

import java.io.Serializable;

public class Entity implements Serializable {

    private static final long serialVersionUID = -1271287718334900043L;

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public Entity() {
    }
    
    public Entity(long id) {
        this.id = id;
    }
}
