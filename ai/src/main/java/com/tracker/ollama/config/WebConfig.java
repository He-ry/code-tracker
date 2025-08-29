package com.tracker.ollama.config;

import com.tracker.framework.config.SpringDocAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public GroupedOpenApi currentGroupedOpenApi() {
        return SpringDocAutoConfiguration.buildGroupedOpenApi("ai", "com.tracker.ollama");
    }
}
