package com.housescent.commonconfiguration.persistence.configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by F4902718 on 16-Nov-15.
 */
@ApplicationScoped
public class EntityManagerProducer {

    @Produces
    @PersistenceContext
    private EntityManager entityManager;

}
