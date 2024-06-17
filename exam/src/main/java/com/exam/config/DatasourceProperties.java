package com.exam.config;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
@Setter
public class DatasourceProperties {
    public DatasourceProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFilePath)) {
            if (input == null) {
                System.out.println("File not found");
                return;
            }
            properties.load(input);
            this.url = properties.getProperty("datasource.url");
            this.username = properties.getProperty("datasource.username");
            this.password = properties.getProperty("datasource.password");
            this.driverClassName = properties.getProperty("datasource.driver-class-name");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private final String propertiesFilePath = "application.properties";
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
