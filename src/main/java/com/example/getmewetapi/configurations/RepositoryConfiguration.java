package com.example.getmewetapi.configurations;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.getmewet.models"})
@EnableJpaRepositories(basePackages = {"com.example.getmewetapi.repositories"})
@EnableTransactionManagement

public class RepositoryConfiguration {
}
