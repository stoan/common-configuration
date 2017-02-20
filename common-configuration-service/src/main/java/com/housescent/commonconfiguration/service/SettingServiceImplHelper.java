package com.housescent.commonconfiguration.service;

import com.housescent.commonconfiguration.persistence.entities.Application;
import com.housescent.commonconfiguration.persistence.entities.Property;
import com.housescent.commonconfiguration.persistence.repositories.ApplicationRepository;
import com.housescent.commonconfiguration.persistence.repositories.PropertyRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by F4902718 - Siya Sosibo on 17-Mar-16 17:09.
 */
@Stateless
public class SettingServiceImplHelper {

    @Inject
    private PropertyRepository propertyRepository;

    @Inject
    private ApplicationRepository applicationRepository;

    public boolean addApplication(String applicationName, String description) throws SettingsException {

        Application application = applicationRepository.saveApplication(applicationName, description);

        return application != null;
    }


    public boolean updateApplication(String applicationName, String description) throws SettingsException {

        Application application = applicationRepository.updateApplication(applicationName, description);

        return application != null;
    }

    public boolean addProperty(String applicationName, String key, String value) throws SettingsException {

        Property property = propertyRepository.saveProperty(applicationName, key, value);

        return property != null;
    }

    public boolean updateProperty(String applicationName, String key, String value) throws SettingsException {

        Property property = propertyRepository.updateProperty(applicationName, key, value);

        return property != null;
    }
}
