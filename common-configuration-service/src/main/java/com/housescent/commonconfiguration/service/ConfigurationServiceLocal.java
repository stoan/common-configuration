package com.housescent.commonconfiguration.service;

import com.housescent.commonconfiguration.api.exception.SettingsException;
import com.housescent.commonconfiguration.persistence.entities.Property;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

/**
 * Created by Siya Sosibo on 13-Jan-16.
 */
@Local
public interface ConfigurationServiceLocal {

    String getPropertyValue(String applicationName, String key) throws SettingsException;

    Map<String, String> getPropertiesForApplicationAsMap(String applicationName) throws SettingsException;

    List<Property> getPropertiesForApplication(String applicationName) throws SettingsException;

    boolean addProperty(String applicationName, String key, String value) throws SettingsException;

    void updateProperty(String applicationName, String key, String value) throws SettingsException;

    void deleteProperty(String applicationName, String key);

    void deleteApplication(String applicationName);

    boolean addApplication(String applicationName, String description) throws SettingsException;

    void updateApplication(String applicationName, String description) throws SettingsException;

}
