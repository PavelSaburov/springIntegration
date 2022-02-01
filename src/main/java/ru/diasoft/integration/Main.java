package ru.diasoft.integration;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.diasoft.integration.service.DemocracyServiceImpl;
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext( Main.class );

        // here we works with cafe using interface
        America america = ctx.getBean( America.class );

        america.sendDemocracy(List.of(
                DemocracyServiceImpl.COUNTRY_RUSSIA,
                DemocracyServiceImpl.COUNTRY_IRAN,
                DemocracyServiceImpl.COUNTRY_AFGHANISTAN
        ));

    }
}
