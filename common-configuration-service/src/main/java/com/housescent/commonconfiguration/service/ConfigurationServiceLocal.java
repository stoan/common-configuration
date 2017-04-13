package com.housescent.commonconfiguration.service;

import com.housescent.commonconfiguration.persistence.entities.Application;
import com.housescent.commonconfiguration.persistence.entities.Property;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

/**
 * Created by Siya Sosibo on 13-Jan-16.
 */
@Local
public interface ConfigurationServiceLocal {

    String fetchPropertyValue(String applicationName, String key);

    Map<String, String> fetchPropertiesForApplicationAsMap(String applicationName);

    List<Property> fetchPropertiesForApplication(String applicationName);

    boolean saveProperty(String applicationName, String key, String value);

    void updateProperty(String applicationName, String key, String value);

    void deleteProperty(String applicationName, String key);

    void deleteApplication(String applicationName);

    boolean saveApplication(String applicationName, String description);

    void updateApplication(String applicationName, String description);

    List<Application> fetchApplications();

    Application fetchApplication(String applicationName);
}
