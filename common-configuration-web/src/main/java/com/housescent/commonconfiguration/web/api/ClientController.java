package com.housescent.commonconfiguration.web.api;

import com.housescent.commonsettings.service.remote.SettingService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Siya Sosibo on 22/02/2017.
 */
@RequestScoped
@Path("/settings/")
public class ClientController {

    private SettingService settingService;

    public ClientController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GET
    @Path("fetchValue")
    public String getProperty(@RequestParam(value = "applicationName") String applicationName, @RequestParam(value = "key") String key) {
        String value = settingService.getPropertyValue(applicationName, key);
        return value;
    }

    @GET
    @Path("fetchProperties")
    public Map<String, String> getSettings(@RequestParam(value = "applicationName") String applicationName) {
        Map<String, String> propertiesForApplicationAsMap = settingService.getPropertiesForApplicationAsMap(applicationName);
        return propertiesForApplicationAsMap;
    }
}
