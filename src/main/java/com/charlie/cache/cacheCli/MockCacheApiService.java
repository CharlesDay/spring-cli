package com.charlie.cache.cacheCli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class MockCacheApiService {

    private static final Logger log = LoggerFactory.getLogger(MockCacheApiService.class);
    // Simulate some persistent cache data on the "server" side for the 'get' command
    private final Map<String, Map<String, String>> mockServerCacheStore = new HashMap<>();
    private final Random random = new Random();

    public String refreshCache(String cacheName) {
        log.info("Simulating API call to REFRESH cache: {}", cacheName);
        // Simulate network delay
        simulateNetworkDelay();

        // Mock Logic: Refresh might involve updating timestamps or reloading specific items
        Map<String, String> cacheData = mockServerCacheStore.computeIfAbsent(cacheName, k -> generateSampleData(k));
        cacheData.put("lastRefreshed", String.valueOf(System.currentTimeMillis()));
        cacheData.put("status", "Refreshed");

        log.info("Mock API: Cache '{}' refreshed successfully.", cacheName);
        return String.format("Success: Cache '%s' refresh signal sent.", cacheName);
    }

    public String refillCache(String cacheName) {
        log.info("Simulating API call to REFILL (clear & load) cache: {}", cacheName);
        // Simulate network delay
        simulateNetworkDelay();

        // Mock Logic: Refill involves clearing existing data and loading fresh data
        mockServerCacheStore.put(cacheName, generateSampleData(cacheName));
        mockServerCacheStore.get(cacheName).put("status", "Refilled");


        log.info("Mock API: Cache '{}' refilled successfully.", cacheName);
        return String.format("Success: Cache '%s' refill signal sent.", cacheName);
    }

    public String getCacheData(String cacheName) {
        log.info("Simulating API call to GET cache data for: {}", cacheName);
        // Simulate network delay
        simulateNetworkDelay();

        // Mock Logic: Return the current state of the cache from our mock store
        Map<String, String> cacheData = mockServerCacheStore.get(cacheName);

        if (cacheData == null) {
            log.warn("Mock API: Cache '{}' not found.", cacheName);
            return String.format("Error: Cache '%s' not found on the server.", cacheName);
        } else {
            log.info("Mock API: Returning data for cache '{}'.", cacheName);
            // Return a simple string representation (e.g., JSON-like)
            return String.format("Data for cache '%s': %s", cacheName, mapToString(cacheData));
        }
    }
    public Set<String> getCacheNames() {
        log.info("Simulating API call to GET all cache names");
        // Simulate network delay
        simulateNetworkDelay();
        return mockServerCacheStore.keySet();
    }

    private void simulateNetworkDelay() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(300) + 100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread interrupted during simulated delay", e);
        }
    }

    private Map<String, String> generateSampleData(String cacheName) {
        Map<String, String> data = new HashMap<>();
        data.put("id", cacheName + "_" + random.nextInt(1000));
        data.put("created", String.valueOf(System.currentTimeMillis()));
        data.put("source", "Mock Generator");
        data.put("itemCount", String.valueOf(random.nextInt(50) + 10));
        return data;
    }

    private String mapToString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder("{");
        map.forEach((key, value) -> sb.append("\"").append(key).append("\":\"").append(value).append("\", "));
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2); // Remove trailing comma and space
        }
        sb.append("}");
        return sb.toString();
    }
}