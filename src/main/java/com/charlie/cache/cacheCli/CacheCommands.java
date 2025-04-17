package com.charlie.cache.cacheCli;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Set;

@ShellComponent
@RequiredArgsConstructor
public class CacheCommands {

    private final MockCacheApiService cacheApiService;

    @ShellMethod(key = "refresh-cache", value = "Sends a request to refresh a specific cache.")
    public String refreshCache(
            @ShellOption(help = "The name of the cache to refresh.") String cacheName
    ) {
        if (cacheName == null || cacheName.trim().isEmpty()) {
            return "Error: Cache name cannot be empty.";
        }
        System.out.println("Sending refresh request for cache: " + cacheName + "...");
        String result = cacheApiService.refreshCache(cacheName);
        return "API Response: " + result;
    }

    @ShellMethod(key = "refill-cache", value = "Sends a request to clear and refill a specific cache.")
    public String refillCache(
            @ShellOption(help = "The name of the cache to refill.") String cacheName
    ) {
        if (cacheName == null || cacheName.trim().isEmpty()) {
            return "Error: Cache name cannot be empty.";
        }
        System.out.println("Sending refill request for cache: " + cacheName + "...");
        String result = cacheApiService.refillCache(cacheName);
        return "API Response: " + result;
    }

    @ShellMethod(key = "get-cache", value = "Sends a request to retrieve the data of a specific cache.")
    public String getCache(
            @ShellOption(help = "The name of the cache to retrieve.") String cacheName
    ) {
        if (cacheName == null || cacheName.trim().isEmpty()) {
            return "Error: Cache name cannot be empty.";
        }
        System.out.println("Sending get request for cache: " + cacheName + "...");
        return cacheApiService.getCacheData(cacheName);
    }

    @ShellMethod(key = "cache-names", value = "Gets all the cache names that the service knows about")
    public Set<String> getCacheNames(
    ) {
        return cacheApiService.getCacheNames();
    }
}