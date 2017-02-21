package com.housescent.commonconfiguration.service;

import com.housescent.commonconfiguration.api.exception.SettingsException;
import com.housescent.commonconfiguration.persistence.entities.Application;
import com.housescent.commonconfiguration.persistence.entities.Property;
import com.housescent.commonconfiguration.persistence.repositories.ApplicationRepository;
import com.housescent.commonconfiguration.persistence.repositories.PropertyRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Siya Sosibo on 17-Mar-16 17:09.
 */
@Stateless
public class SettingServiceImplHelper {

    @Inject
    private PropertyRepository propertyRepository;

    @Inject
    private ApplicationRepository applicationRepository;

    public boolean addApplication(String applicationName, String description) throws SettingsException {

        Application application = applicationRepository.save(new Application(applicationName,description));

        return application.getId() > 0;
    }

    public void updateApplication(String applicationName, String description) throws SettingsException {

        Application application = applicationRepository.findByApplicationname(applicationName);
        application.setDescription(description);
    }

    public boolean addProperty(String applicationName, String key, String value) throws SettingsException {

        Application application = applicationRepository.findByApplicationname(applicationName);

        if (application == null) {
          application = applicationRepository.save(new Application(applicationName,applicationName));
        }

        Property property = propertyRepository.save(new Property(key, value, application));

        return property.getId() > 0;
    }

    public void updateProperty(String applicationName, String key, String value) throws SettingsException {

        Property property = propertyRepository.findByApplication_applicationNameAndKey(applicationName, key);
        property.setValue(value);
    }
}
