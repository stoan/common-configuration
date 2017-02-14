package com.housescent.commonconfiguration.persistence.entities;

import javax.persistence.*;

/**
 * Created by S983620 on 13/02/2017.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
