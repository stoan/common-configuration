package com.housescent.commonconfiguration.service;

import com.housescent.commonconfiguration.api.exception.DuplicateSettingException;
import com.housescent.commonconfiguration.api.exception.SettingNotFoundException;
import com.housescent.commonconfiguration.persistence.entities.Application;
import com.housescent.commonconfiguration.persistence.entities.Property;
import com.housescent.commonconfiguration.persistence.repositories.ApplicationRepository;
import com.housescent.commonconfiguration.persistence.repositories.PropertyRepository;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Siya Sosibo on 17-Mar-16 17:09.
 */
@Stateless
public class SettingServiceImplHelper {

    public static final String PROPERTY_NOT_FOUND = "Property not found";
    public static final String APPLICATION_NOT_FOUND = "Application not found";
    public static final String APPLICATION_ALREADY_EXISTS = "Application already exists";
    public static final String PROPERTY_ALREADY_EXIST = "Property already exist";

    @Inject
    private PropertyRepository propertyRepository;

    @Inject
    private ApplicationRepository applicationRepository;

    public boolean addApplication(String applicationName, String description) {

        List<Application> applications = applicationRepository.findByApplicationNameIgnoreCase(applicationName);

        if (CollectionUtils.isNotEmpty(applications)) {
            throw new DuplicateSettingException(APPLICATION_ALREADY_EXISTS);
        }

        Application application = applicationRepository.save(new Application(applicationName,description));

        return application.getId() > 0;
    }

    public void updateApplication(String applicationName, String description) {

        List<Application> applications = applicationRepository.findByApplicationNameIgnoreCase(applicationName);

        if (CollectionUtils.isEmpty(applications)) {

            throw new SettingNotFoundException(APPLICATION_NOT_FOUND);
        }

        applications.get(0).setDescription(description);
    }

    public boolean addProperty(String applicationName, String key, String value) {

        List<Application> applications = applicationRepository.findByApplicationNameIgnoreCase(applicationName);

        if (CollectionUtils.isEmpty(applications)) {
            throw new SettingNotFoundException(APPLICATION_NOT_FOUND);
        }

        Property property = propertyRepository.findByApplication_applicationNameIgnoreCaseAndKeyIgnoreCase(applicationName, key);
        if (property != null) {
            throw new DuplicateSettingException(PROPERTY_ALREADY_EXIST);
        }

        property = propertyRepository.save(new Property(key, value, applications.get(0)));

        return property.getId() > 0;
    }

    public void updateProperty(String applicationName, String key, String value) {

        Property property = propertyRepository.findByApplication_applicationNameIgnoreCaseAndKeyIgnoreCase(applicationName, key);
        if (property == null) {
            throw new SettingNotFoundException(PROPERTY_NOT_FOUND);
        }

        property.setValue(value);
    }
}
