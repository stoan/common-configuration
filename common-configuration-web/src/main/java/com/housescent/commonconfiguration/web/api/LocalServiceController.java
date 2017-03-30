package com.housescent.commonconfiguration.web.api;


import com.housescent.commonconfiguration.persistence.entities.Application;
import com.housescent.commonconfiguration.persistence.entities.Property;
import com.housescent.commonconfiguration.service.ConfigurationServiceLocal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Siya Sosibo on 22/02/2017.
 */
@RequestScoped
@Path("/admin/")
public class LocalServiceController {

    private ConfigurationServiceLocal localSettingService;

    @Inject
    public LocalServiceController(ConfigurationServiceLocal localSettingService) {
        this.localSettingService = localSettingService;
    }

    @GET
    @Path("fetchPropertiesForApplication")
    @Produces("application/json")
    public List<Property> getPropertiesForApplication(@QueryParam(value = "applicationName") String applicationName) {
        return localSettingService.getPropertiesForApplication(applicationName);
    }

    @POST
    @Path("saveProperty")
    public boolean addProperty(@QueryParam(value = "applicationName") String applicationName, @QueryParam(value = "key") String key, @QueryParam(value = "value") String value) {
        return localSettingService.addProperty(applicationName, key, value);
    }

    @POST
    @Path("updateProperty")
    public void updateProperty(@QueryParam(value = "applicationName") String applicationName, @QueryParam(value = "key") String key, @QueryParam(value = "value") String value) {
        localSettingService.updateProperty(applicationName, key, value);
    }

    @DELETE
    @Path("deleteProperty")
    public void deleteProperty(@QueryParam(value = "applicationName") String applicationName, @QueryParam(value = "key") String key) {
        localSettingService.deleteProperty(applicationName, key);
    }

    @POST
    @Path("saveApplication")
    public boolean addApplication(@QueryParam(value = "applicationName") String applicationName, @QueryParam(value = "description") String description) {
        return localSettingService.addApplication(applicationName, description);
    }

    @GET
    @Path("fetchApplications")
    @Produces("application/json")
    public List<Application> getApplications() {
        return localSettingService.getApplications();
    }

    @DELETE
    @Path("deleteApplication")
    public void deleteApplication(@QueryParam(value = "applicationName") String applicationName) {
        localSettingService.deleteApplication(applicationName);
    }

    @POST
    @Path("updateApplication")
    public void updateApplication(@QueryParam(value = "applicationName") String applicationName, @QueryParam(value = "description") String description) {
        localSettingService.updateApplication(applicationName, description);
    }

    @GET
    @Path("fetchApplication")
    @Produces("application/json")
    public Application getApplication(@QueryParam(value = "applicationName") String applicationName) {
        return localSettingService.getApplication(applicationName);
    }
}
