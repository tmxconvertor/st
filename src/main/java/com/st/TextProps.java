package com.st;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource( "classpath:text.properties")
@ComponentScan(basePackages = "com.st")
public class TextProps {


    @Value("${property}")
     private String text;
    @Value("a")
     private String another;


    String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
