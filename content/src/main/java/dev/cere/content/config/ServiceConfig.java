package dev.cere.content.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "dev.cere.content.data.repository")
public class ServiceConfig {}
