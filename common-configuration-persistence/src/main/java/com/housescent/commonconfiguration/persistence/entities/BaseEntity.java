package com.housescent.commonconfiguration.persistence.entities;

import javax.persistence.*;

/**
 * Created by Siya Sosibo on 13/02/2017.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
