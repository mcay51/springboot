package tr.com.mcay.springbootmodulerlearning.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ValueController {

    @Value("${app.name}")
    private String appName;
    @Value("${app.version}")
    private String appVersion;
    @Value("${app.description}")
    private String appDescription;

    @GetMapping("/app-info")
    public String getAppInfo() {
        return "App Name: " + appName +
                ", Version: " + appVersion+
                ", Description: "+appDescription;
    }
}
