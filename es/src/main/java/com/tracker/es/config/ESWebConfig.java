package com.tracker.es.config;

import com.tracker.framework.config.swagger.SpringDocAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESWebConfig {

    @Bean
    public GroupedOpenApi ESGroupedOpenApi() {
        return SpringDocAutoConfiguration.buildGroupedOpenApi("ES模块", "com.tracker.es");
    }
}
