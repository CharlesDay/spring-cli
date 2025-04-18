package com.charlie.cache.cacheCli;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class StartupMessageRunner {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    @PostConstruct // This method runs after bean construction and dependency injection
    public void init() {
        System.out.println("--- Message from @PostConstruct ---");
        System.out.println("\n" + GREEN + " You can type 'help' for a list of commands" + RESET + "\n");
        System.out.println("------------------------------------\n");
    }
}