package com.housescent.commonconfiguration.service;


import com.housescent.commonconfiguration.persistence.entities.Property;
import com.housescent.commonconfiguration.persistence.repositories.ApplicationRepository;
import com.housescent.commonconfiguration.persistence.repositories.PropertyRepository;
import org.apache.commons.collections4.CollectionUtils;


import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by F4902718 on 13-Jan-16.
 */
@Stateless
@Remote(ConfigurationServiceRemote.class)
public class ConfigurationServiceImpl implements ConfigurationServiceLocal, ConfigurationServiceRemote {

    @EJB
    private SettingServiceImplHelper settingServiceImplHelper;
    @Inject
    private PropertyRepository propertyRepository;

    @Inject
    private ApplicationRepository applicationRepository;

    @Override
    public String getPropertyValue(String applicationName, String key) throws DuplicateSettingException, SettingNotFoundException {
        List<Property> properties = propertyRepository.getPropertyValue(applicationName, key);

        if (CollectionUtils.isNotEmpty(properties) && properties.size() == 1) {
            return properties.get(0).getValue();
        } else if (CollectionUtils.isNotEmpty(properties) && properties.size() > 1) {
            throw new DuplicateSettingException("Multiple properties found for application name: " + applicationName + " and key: " + key);
        } else {
            throw new SettingNotFoundException("Property value not found for application name: " + applicationName + " and key: " + key);
        }
    }

    @Override
    public List<Property> getPropertiesForApplication(String applicationName) throws DuplicateSettingException, SettingNotFoundException {
        List<Property> properties = propertyRepository.getPropertiesForApplication(applicationName);

        if (org.apache.commons.collections4.CollectionUtils.isEmpty(properties)) {
            throw new SettingNotFoundException("Application settings not found for application name: " + applicationName);
        }

        return properties;
    }

    @Deprecated
    @Override
    public Map<String, String> getPropertiesForApplicationAsMap(String applicationName) throws DuplicateSettingException, SettingNotFoundException {
        Map<String, String> properties = propertyRepository.getPropertiesForApplicationAsMap(applicationName);

        if (properties == null || properties.isEmpty()) {
            throw new SettingNotFoundException("Application settings not found for application name: " + applicationName);
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
    public boolean updateProperty(String applicationName, String key, String value) throws SettingsException {
        boolean isSaved;
        try {
            isSaved = settingServiceImplHelper.updateProperty(applicationName, key, value);
        } catch (Exception e) {
            throw new SettingsException("Property not updated" + System.lineSeparator() + e.getMessage());
        }

        return isSaved;
    }

    @Override
    public void deleteProperty(String applicationName, String key) {
        propertyRepository.deteleProperty(applicationName, key);
    }

    @Override
    public void deleteAllApplication(String applicationName) {
        applicationRepository.deleteApplication(applicationName);
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
    public boolean updateApplication(String applicationName, String description) throws SettingsException {
        boolean isSaved;
        try {
            isSaved = settingServiceImplHelper.updateApplication(applicationName, description);
        } catch (Exception e) {
            throw new SettingsException("Application not updated" + System.lineSeparator() + e.getMessage());
        }
        return isSaved;
    }
}
