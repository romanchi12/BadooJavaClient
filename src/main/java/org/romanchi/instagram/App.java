package org.romanchi.instagram;

import org.romanchi.instagram.api.ApiClient;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = JmxAutoConfiguration.class)
public class App {
    public static void main( String[] args ) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class).web(WebApplicationType.SERVLET);
        ApplicationContext context = builder.run(args);
        ApiClient apiClient = context.getBean(ApiClient.class);
        apiClient.login();

    }
}
