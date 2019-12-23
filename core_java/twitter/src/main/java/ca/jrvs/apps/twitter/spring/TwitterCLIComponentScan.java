package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIApp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@ComponentScan("ca.jrvs.apps.twitter")
public class TwitterCLIComponentScan {

        public static void main(String[] args) throws IOException, URISyntaxException {
            ApplicationContext context = new AnnotationConfigApplicationContext(ca.jrvs.apps.twitter.spring.TwitterCLIComponentScan.class);
            TwitterCLIApp app = context.getBean(TwitterCLIApp.class);
            app.run(args);
        }
}
