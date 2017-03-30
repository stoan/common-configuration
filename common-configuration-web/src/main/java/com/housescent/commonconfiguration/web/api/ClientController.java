package com.housescent.commonconfiguration.web.api;

import com.housescent.commonconfiguration.api.service.ConfigurationServiceRemote;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by Siya Sosibo on 22/02/2017.
 */
@RequestScoped
@Path("/settings/")
public class ClientController {

    private ConfigurationServiceRemote settingService;

    @Inject
    public ClientController(ConfigurationServiceRemote settingService) {
        this.settingService = settingService;
    }

    @GET
    @Path("fetchValue")
    public Response getProperty(@QueryParam(value = "applicationName") String applicationName, @QueryParam(value = "key") String key) {
        String value = settingService.getPropertyValue(applicationName, key);
        return Response.status(200).entity(value).build();
    }

    @GET
    @Path("fetchProperties")
    @Produces("application/json")
    public Map<String, String> getSettings(@QueryParam(value = "applicationName") String applicationName) {
        Map<String, String> propertiesForApplicationAsMap = settingService.getPropertiesForApplicationAsMap(applicationName);
        return propertiesForApplicationAsMap;
    }
}
