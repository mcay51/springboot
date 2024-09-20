package tr.com.mcay.springbootmodulerlearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.mcay.springbootmodulerlearning.configurations.AppProperties;

@RestController
@RequestMapping("/api")
public class ConfigPropertiesController {
    private final AppProperties appProperties;

    public ConfigPropertiesController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }
    @GetMapping("/config-info")
    public String getConfigInfo(){
        return "App Name: " + appProperties.getName() +
                ", Version: " + appProperties.getVersion() +
                ", Description: " + appProperties.getDescription();
    }
}
