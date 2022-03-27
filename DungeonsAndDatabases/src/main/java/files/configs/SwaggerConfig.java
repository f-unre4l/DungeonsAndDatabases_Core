package files.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "DungeonsAndDatabase Core API",
                "API for the DungeonsAndDatabase Core Application. Holds the business logic of the project. " +
                "Holds the basic information of the heroes in a storage.",
                "1.0",
                null,
                new springfox.documentation.service.Contact("DungeonsAndDatabases", "https://github.com/f-unre4l/DungeonsAndDatabases_Core", null)
                , null
                , null
                , Collections.emptyList()
        );
    }
}
