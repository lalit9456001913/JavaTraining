package com.example.sbfeb7;

import com.example.sbfeb7.resources.companies.entities.Company;
import com.example.sbfeb7.resources.users.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication

public class DemoApplication implements RepositoryRestConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        //Expose ids for these resources. Spring boot by itself does not expose ids.
        config.exposeIdsFor(Company.class);
        config.exposeIdsFor(User.class);
        //Do not automatically expose rest repositories. Only expose if annotated as @RestRepositoryResource
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
    }
}

