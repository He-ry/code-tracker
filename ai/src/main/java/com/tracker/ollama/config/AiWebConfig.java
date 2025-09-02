package com.tracker.ollama.config;

import com.tracker.framework.config.swagger.SpringDocAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiWebConfig {

    @Bean
    public GroupedOpenApi currentGroupedOpenApi() {
        return SpringDocAutoConfiguration.buildGroupedOpenApi("ai", "com.tracker.ollama.controller");
    }
}
