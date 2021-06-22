package pl.coderslab.charity.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"pl.coderslab.charity"})
@EntityScan(basePackages = {"pl.coderslab.charity.entity"})
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String[] STATIC_RESOURCE = {"/","classpath:/","classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/",
                "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

            registry.addResourceHandler("/**").addResourceLocations(STATIC_RESOURCE);
            registry.addResourceHandler("/donation/**").addResourceLocations(STATIC_RESOURCE);
    }

}
