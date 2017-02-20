package com.housescent.commonconfiguration.persistence.repositories;

import com.housescent.commonconfiguration.persistence.entities.Application;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by s983620 on 16/02/2017.
 */
@RunWith(CdiTestRunner.class)
public class ApplicationRepositoryTest {

    @Inject
    private ApplicationRepository applicationRepository;

    @Test
    public void saveApplicationTest() throws Exception {
        Application application = new Application();
        application.setApplicationName("Test App");
        application.setDescription("Test App Description");

        applicationRepository.save(application);

    }
}