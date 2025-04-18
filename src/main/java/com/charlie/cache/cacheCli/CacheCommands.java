package com.charlie.cache.cacheCli;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Set;

/**
 * Provides command-line operations for interacting with a cache service
 * using Spring Shell. Commands include refreshing, refilling, retrieving,
 * and listing caches.
 */
@ShellComponent
@RequiredArgsConstructor
public class CacheCommands {

    private final MockCacheApiService cacheApiService;
    private final PlaceholderService placeholderService;

    /**
     * Refreshes a specific cache by its name.
     *
     * @param cacheName the name of the cache to refresh
     * @return the response from the API indicating the result of the refresh
     */
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

    /**
     * Clears and refills a specific cache by its name.
     *
     * @param cacheName the name of the cache to refill
     * @return the response from the API indicating the result of the refill
     */
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

    /**
     * Retrieves the data stored in a specific cache.
     *
     * @param cacheName the name of the cache to retrieve
     * @return the contents of the specified cache
     */
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

    /**
     * Retrieves the names of all caches known to the service.
     *
     * @return a set of cache names
     */
    @ShellMethod(key = "cache-names", value = "Gets all the cache names that the service knows about")
    public Set<String> getCacheNames() {
        return cacheApiService.getCacheNames();
    }

    @ShellMethod(key = "get-product", value = "Gets a product by ID")
    public String getProductById(@ShellOption(help = "The id of the product.") String id) {
        if (id == null || id.trim().isEmpty()) {
            return "Error: Product ID cannot be empty.";
        }
        System.out.println("Sending get request for product: " + id + "...");
        return placeholderService.getProducts(id);
    }
}
