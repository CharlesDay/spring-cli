package com.charlie.cache.cacheCli;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CacheCommandsTest {

    @Mock
    MockCacheApiService mockCacheApiService;

    @InjectMocks
    CacheCommands cacheCommands;

    @Nested
    class RefillCacheTests {

        @Test
        void testRefillCache_cacheNameIsNull_returnsErrorMessage() {
            String result = cacheCommands.refillCache(null);
            assertEquals("Error: Cache name cannot be empty.", result);
        }

        @Test
        void testRefillCache_cacheNameIsEmpty_returnsErrorMessage() {
            String result = cacheCommands.refillCache("");
            assertEquals("Error: Cache name cannot be empty.", result);
        }

        @Test
        void testRefillCache_cacheNameIsValid_returnsResultFromCacheApiService() {
            Mockito.when(mockCacheApiService.refillCache("some-name"))
                    .thenReturn("Success: Cache 'some-name' refill signal sent.");
            String result = cacheCommands.refillCache("some-name");
            assertEquals("API Response: Success: Cache 'some-name' refill signal sent.", result);
        }
    }

    @Nested
    class RefreshCacheTests {

        @Test
        void testRefreshCache_cacheNameIsNull_returnsErrorMessage() {
            String result = cacheCommands.refreshCache(null);
            assertEquals("Error: Cache name cannot be empty.", result);
        }

        @Test
        void testRefreshCache_cacheNameIsEmpty_returnsErrorMessage() {
            String result = cacheCommands.refreshCache("");
            assertEquals("Error: Cache name cannot be empty.", result);
        }

        @Test
        void testRefreshCache_cacheNameIsValid_returnsResultFromCacheApiService() {
            Mockito.when(mockCacheApiService.refreshCache("some-name"))
                    .thenReturn("Success: Cache 'some-name' refresh signal sent.");
            String result = cacheCommands.refreshCache("some-name");
            assertEquals("API Response: Success: Cache 'some-name' refresh signal sent.", result);
        }
    }

    @Nested
    class GetCacheTests {

        @Test
        void testGetCache_cacheNameIsNull_returnsErrorMessage() {
            String result = cacheCommands.getCache(null);
            assertEquals("Error: Cache name cannot be empty.", result);
        }

        @Test
        void testGetCache_cacheNameIsEmpty_returnsErrorMessage() {
            String result = cacheCommands.getCache("");
            assertEquals("Error: Cache name cannot be empty.", result);
        }

        @Test
        void testGetCache_cacheNameIsValid_returnsResultFromCacheApiService() {
            Mockito.when(mockCacheApiService.getCacheData("some-name"))
                    .thenReturn("Data for cache 'some-name': {key1=value1, key2=value2}");
            String result = cacheCommands.getCache("some-name");
            assertEquals("Data for cache 'some-name': {key1=value1, key2=value2}", result);
        }
    }

    @Nested
    class GetCacheNamesTests {

        @Test
        void testGetCacheNames_returnsSetOfCacheNames() {
            Mockito.when(mockCacheApiService.getCacheNames())
                    .thenReturn(Set.of("cache1", "cache2"));
            Set<String> result = cacheCommands.getCacheNames();
            assertEquals(Set.of("cache1", "cache2"), result);
        }
    }
}