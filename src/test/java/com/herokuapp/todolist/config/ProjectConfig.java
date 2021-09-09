package com.herokuapp.todolist.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
})
public interface ProjectConfig extends Config {

    @DefaultValue("chrome")
    @Key("browser")
    String browser();

    @DefaultValue("91.0")
    @Key("browserVersion")
    String browserVersion();

    @DefaultValue("https://api-nodejs-todolist.herokuapp.com")
    @Key("baseUrl")
    String baseUrl();

    @Key("remoteDriverUrl")
    String remoteDriverUrl();
}
