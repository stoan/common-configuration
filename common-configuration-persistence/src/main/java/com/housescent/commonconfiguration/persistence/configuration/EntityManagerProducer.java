package com.housescent.commonconfiguration.persistence.configuration;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Siya Sosibo on 16-Nov-15.
 */
public class EntityManagerProducer {
    @Produces
    @PersistenceContext
    private EntityManager em;

}
