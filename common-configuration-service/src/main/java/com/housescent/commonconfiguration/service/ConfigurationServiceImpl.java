package com.housescent.commonconfiguration.service;


import com.housescent.commonconfiguration.api.exception.SettingNotFoundException;
import com.housescent.commonconfiguration.api.exception.SettingsException;
import com.housescent.commonconfiguration.api.service.ConfigurationServiceRemote;
import com.housescent.commonconfiguration.persistence.entities.Application;
import com.housescent.commonconfiguration.persistence.entities.Property;
import com.housescent.commonconfiguration.persistence.repositories.ApplicationRepository;
import com.housescent.commonconfiguration.persistence.repositories.PropertyRepository;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.housescent.commonconfiguration.service.SettingServiceImplHelper.*;

/**
 * Created by  Siya Sosibo on 13-Jan-16.
 */
@Stateless
@Remote(ConfigurationServiceRemote.class)
public class ConfigurationServiceImpl implements ConfigurationServiceLocal, ConfigurationServiceRemote {

    public static final String APPLICATION_SETTINGS_NOT_FOUND_FOR_APPLICATION_NAME = "Application settings not found for application name: ";
    @EJB
    private SettingServiceImplHelper settingServiceImplHelper;
    @Inject
    private PropertyRepository propertyRepository;

    @Inject
    private ApplicationRepository applicationRepository;

    @Override
    public String fetchPropertyValue(String applicationName, String key) {
        List<Property> properties = propertyRepository.findByApplication_applicationNameIgnoreCaseAndKeyIgnoreCase(applicationName, key);

        if (CollectionUtils.isNotEmpty(properties)) {
            return properties.get(0).getValue();
        } else {
            throw new SettingNotFoundException(PROPERTY_NOT_FOUND);
        }
    }

    @Override
    public List<Property> fetchPropertiesForApplication(String applicationName) {
        List<Property> properties = propertyRepository.findByApplication_applicationNameIgnoreCase(applicationName);

        if (org.apache.commons.collections4.CollectionUtils.isEmpty(properties)) {
            throw new SettingNotFoundException(APPLICATION_SETTINGS_NOT_FOUND_FOR_APPLICATION_NAME + applicationName);
        }

        return properties;
    }

    @Override
    public Map<String, String> fetchPropertiesForApplicationAsMap(String applicationName) {
        List<Property> props = propertyRepository.findByApplication_applicationNameIgnoreCase(applicationName);

        if (org.apache.commons.collections4.CollectionUtils.isEmpty(props)) {
            throw new SettingNotFoundException(APPLICATION_SETTINGS_NOT_FOUND_FOR_APPLICATION_NAME + applicationName);
        }

        Map<String, String> properties = new HashMap<>();
        for (Property prop : props) {
            properties.put(prop.getKey(), prop.getValue());
        }
        return properties;
    }


    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public boolean saveProperty(String applicationName, String key, String value) {
        boolean isSaved;
        try {
            isSaved = settingServiceImplHelper.saveProperty(applicationName, key, value);
        } catch (Exception e) {
            throw new SettingsException("Property not created, Property key: " + key + " may already exist" + System.lineSeparator() + e.getMessage());
        }

        return isSaved;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void updateProperty(String applicationName, String key, String value) {

        try {
           settingServiceImplHelper.updateProperty(applicationName, key, value);
        } catch (Exception e) {
            throw new SettingsException("Property not updated" + System.lineSeparator() + e.getMessage());
        }
    }

    @Override
    public void deleteProperty(String applicationName, String key) {
        List<Property> properties = propertyRepository.findByApplication_applicationNameIgnoreCaseAndKeyIgnoreCase(applicationName, key);

        if (CollectionUtils.isEmpty(properties)) {
            throw new SettingNotFoundException(PROPERTY_NOT_FOUND);
        }

            propertyRepository.remove(properties.get(0));

    }

    @Override
    public void deleteApplication(String applicationName) {
        List<Application> applications = applicationRepository.findByApplicationNameIgnoreCase(applicationName);

        if (CollectionUtils.isEmpty(applications)) {
            throw new SettingNotFoundException(APPLICATION_NOT_FOUND);
        }
            applicationRepository.remove(applications.get(0));

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public boolean saveApplication(String applicationName, String description) {
        boolean isSaved;
        try {
            isSaved = settingServiceImplHelper.saveApplication(applicationName, description);
        } catch (Exception e) {
            throw new SettingsException("Application not created, application Name: " + applicationName + " may already exist" + System.lineSeparator() + e.getMessage());
        }

        return isSaved;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void updateApplication(String applicationName, String description) {
        try {
            settingServiceImplHelper.updateApplication(applicationName, description);
        } catch (Exception e) {
            throw new SettingsException("Application not updated" + System.lineSeparator() + e.getMessage());
        }
    }

    @Override
    public List<Application> fetchApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Application fetchApplication(String applicationName) {
        List<Application> applications = applicationRepository.findByApplicationNameIgnoreCase(applicationName);

        if (CollectionUtils.isEmpty(applications)) {
            throw new SettingNotFoundException(APPLICATION_NOT_FOUND);
        } else {
            return applications.get(0);
        }
    }
}
