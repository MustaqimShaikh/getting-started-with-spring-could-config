package com.startspringcloud.productservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
public class ConfigValueController {


    //MySQL Values
    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddlAuto;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String className;
    @Value("${spring.jpa.show-sql}")
    Boolean showSql;

    //Environment Values
    @Value("${top.environment}")
    String topEnv;
    @Value("${sub.environment}")
    String subEnv;

    //Global Values
    @Value("${application.name}")
    String applicationName;
    @Value("${application.id}")
    String applicationId;

    //Default Values
    @Value("${config.folder.name}")
    String configFolderName;
    @Value("${version}")
    Double version;
    @Value("${phase}")
    String phase;
    @Value("${phase.code}")
    String phaseCode;

    //Encrypted Values
//    @Value("${connstring}")
//    String connString;
    @GetMapping("/config/values")
    public ResponseEntity<Map<Object, Object>> getRate() {
        Map<Object, Object> response = new HashMap<>();
        response.put("globalValues", getGlobalProperties());
        response.put("environmentsValues", getEnvironments());
        response.put("defaultValue", getDefaultProperties());
        response.put("mySqlProperties", getMysqlProperties());
       // response.put("connString", connString);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public Map<String, Object> getDefaultProperties() {
        Map<String, Object> defaultConfig = new HashMap<>();
        defaultConfig.put("configFolderName", configFolderName);
        defaultConfig.put("version", version);
        defaultConfig.put("phase", phase);
        defaultConfig.put("phaseCode", phaseCode);
        return defaultConfig;
    }

    public Map<String, String> getGlobalProperties() {
        Map<String, String> globalConfig = new HashMap<>();
        globalConfig.put("applicationName", applicationName);
        globalConfig.put("applicationId", applicationId);
        return globalConfig;
    }

    public Map<String, String> getEnvironments() {
        Map<String, String> environments = new HashMap<>();
        environments.put("topEnv", topEnv);
        environments.put("subEnv", subEnv);
        return environments;
    }

    public Map<String, Object> getMysqlProperties() {
        Map<String, Object> mySqlConfig = new HashMap<>();
        mySqlConfig.put("ddlAuto", ddlAuto);
        mySqlConfig.put("url", url);
        mySqlConfig.put("userName", userName);
        mySqlConfig.put("password", password);
        mySqlConfig.put("className", className);
        mySqlConfig.put("showSql", showSql);
        return mySqlConfig;
    }

}
