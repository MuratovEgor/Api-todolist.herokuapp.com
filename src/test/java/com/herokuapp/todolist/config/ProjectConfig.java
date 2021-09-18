package com.herokuapp.todolist.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
})
public interface ProjectConfig extends Config {
    @Key("baseUrl")
    String baseUrl();
}
