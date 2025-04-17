package com.charlie.cache.cacheCli;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class StartupMessageRunner {

    @PostConstruct // This method runs after bean construction and dependency injection
    public void init() {
        System.out.println("--- Message from @PostConstruct ---");
        System.out.println(" You can type 'help' for a list of commands");
        System.out.println("------------------------------------\n");
    }
}