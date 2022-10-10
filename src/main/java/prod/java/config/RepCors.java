package prod.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RepCors implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        try {
            config.setBasePath(String.valueOf(new URI("/api/")));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
