package com.housescent.commonconfiguration.service;


import com.housescent.commonconfiguration.api.exception.SettingNotFoundException;
import com.housescent.commonconfiguration.api.exception.SettingsException;
import com.housescent.commonconfiguration.api.service.ConfigurationServiceRemote;
import com.housescent.commonconfiguration.persistence.entities.Application;
import com.housescent.commonconfiguration.persistence.entities.Property;
import com.housescent.commonconfiguration.persistence.repositories.ApplicationRepository;
import com.housescent.commonconfiguration.persistence.repositories.PropertyRepository;

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
    public String getPropertyValue(String applicationName, String key) throws SettingNotFoundException {
        Property property = propertyRepository.findByApplication_applicationNameIgnoreCaseAndKeyIgnoreCase(applicationName, key);

        if (property != null) {
            return property.getValue();
        } else {
            throw new SettingNotFoundException(PROPERTY_NOT_FOUND);
        }
    }

    @Override
    public List<Property> getPropertiesForApplication(String applicationName) throws SettingNotFoundException {
        List<Property> properties = propertyRepository.findByApplication_applicationNameIgnoreCase(applicationName);

        if (org.apache.commons.collections4.CollectionUtils.isEmpty(properties)) {
            throw new SettingNotFoundException(APPLICATION_SETTINGS_NOT_FOUND_FOR_APPLICATION_NAME + applicationName);
        }

        return properties;
    }

    @Override
    public Map<String, String> getPropertiesForApplicationAsMap(String applicationName) throws SettingNotFoundException {
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
    public boolean addProperty(String applicationName, String key, String value) throws SettingsException {
        boolean isSaved;
        try {
            isSaved = settingServiceImplHelper.addProperty(applicationName, key, value);
        } catch (Exception e) {
            throw new SettingsException("Property not created, Property key: " + key + " may already exist" + System.lineSeparator() + e.getMessage());
        }

        return isSaved;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void updateProperty(String applicationName, String key, String value) throws SettingsException {

        try {
           settingServiceImplHelper.updateProperty(applicationName, key, value);
        } catch (Exception e) {
            throw new SettingsException("Property not updated" + System.lineSeparator() + e.getMessage());
        }
    }

    @Override
    public void deleteProperty(String applicationName, String key) throws SettingNotFoundException {
        Property property = propertyRepository.findByApplication_applicationNameIgnoreCaseAndKeyIgnoreCase(applicationName, key);

        if (property == null) {
            throw new SettingNotFoundException(PROPERTY_NOT_FOUND);
        }

            propertyRepository.remove(property);

    }

    @Override
    public void deleteApplication(String applicationName) throws SettingNotFoundException {
        Application application = applicationRepository.findByApplicationNameIgnoreCase(applicationName);

        if (application == null) {
            throw new SettingNotFoundException(APPLICATION_NOT_FOUND);
        }
            applicationRepository.remove(application);

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public boolean addApplication(String applicationName, String description) throws SettingsException {
        boolean isSaved;
        try {
            isSaved = settingServiceImplHelper.addApplication(applicationName, description);
        } catch (Exception e) {
            throw new SettingsException("Application not created, application Name: " + applicationName + " may already exist" + System.lineSeparator() + e.getMessage());
        }

        return isSaved;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void updateApplication(String applicationName, String description) throws SettingsException {
        try {
            settingServiceImplHelper.updateApplication(applicationName, description);
        } catch (Exception e) {
            throw new SettingsException("Application not updated" + System.lineSeparator() + e.getMessage());
        }
    }
}
