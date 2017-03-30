package com.housescent.commonconfiguration.web.api;

import com.housescent.commonsettings.persistence.entities.Application;
import com.housescent.commonsettings.persistence.entities.Property;
import com.housescent.commonsettings.service.local.LocalSettingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Siya Sosibo on 22/02/2017.
 */
@RequestScoped
@Path("/admin/")
public class LocalServiceController {

    private LocalSettingService localSettingService;

    public LocalServiceController(LocalSettingService localSettingService) {
        this.localSettingService = localSettingService;
    }

    @GET
    @Path("fetchPropertiesForApplication")
    public List<Property> getPropertiesForApplication(@RequestParam(value = "applicationName") String applicationName) {
        return localSettingService.getPropertiesForApplication(applicationName);
    }

    @POST
    @Path("saveProperty")
    public boolean addProperty(@RequestParam(value = "applicationName") String applicationName, @RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        return localSettingService.addProperty(applicationName, key, value);
    }

    @POST
    @Path("updateProperty")
    public void updateProperty(@RequestParam(value = "applicationName") String applicationName, @RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        localSettingService.updateProperty(applicationName, key, value);
    }

    @DELETE
    @Path("deleteProperty")
    public void deleteProperty(@RequestParam(value = "applicationName") String applicationName, @RequestParam(value = "key") String key) {
        localSettingService.deleteProperty(applicationName, key);
    }

    @POST
    @Path("saveApplication")
    public boolean addApplication(@RequestParam(value = "applicationName") String applicationName, @RequestParam(value = "description") String description) {
        return localSettingService.addApplication(applicationName, description);
    }

    @GET
    @Path("fetchApplications")
    public List<Application> getApplications() {
        return localSettingService.getApplications();
    }

    @DELETE
    @Path("deleteApplication")
    public void deleteApplication(@RequestParam(value = "applicationName") String applicationName) {
        localSettingService.deleteApplication(applicationName);
    }

    @POST
    @Path("updateApplication")
    public void updateApplication(@RequestParam(value = "applicationName") String applicationName, @RequestParam(value = "description") String description) {
        localSettingService.updateApplication(applicationName, description);
    }

    @GET
    @Path("fetchApplication")
    public Application getApplication(@RequestParam(value = "applicationName") String applicationName) {
        return localSettingService.getApplication(applicationName);
    }
}
