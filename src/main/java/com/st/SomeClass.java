package com.st;

import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("singleton")
//@Scope("prototype")
class SomeClass {

    private static int count = 0;
    private int number;

//    @Autowired()
    TextProps text;

//    @Value("${property}")
    private String property;

    int getNumber() {
        return number;
    }

    void setText(TextProps text) {
        this.text = text;
    }
    private WebClient root;

    @Autowired
    SomeClass( TextProps props, @Value("${property}") String internal) {

        number = count++;
        text = props;
        property = internal;
        System.out.println("#"+number+", property="+property+", text="+text);
//        throw new RuntimeException("test on creation");
        root = new WebClient();
    }

    @Bean
    @Scope("prototype")
    WebClient getWebClient(){
        WebClient webClient = new WebClient();
        webClient.setCookieManager(root.getCookieManager());
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        return webClient;
    }

}
