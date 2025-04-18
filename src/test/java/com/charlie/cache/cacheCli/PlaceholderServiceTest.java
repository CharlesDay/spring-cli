package com.charlie.cache.cacheCli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaceholderServiceTest {

    /**
     * A deep stub mock of {@link RestClient} allows for fluent API mocking.
     * <p>
     * This enables chaining calls like {@code restClient.get().uri(...).retrieve().body(...)}
     * without having to mock each intermediate return object manually.
     * </p>
     */
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RestClient restClient;

    private PlaceholderService placeholderService;

    private String placeholderApiUrl = "http://mock-api.com";

    @BeforeEach
    void setUp() {
        placeholderService = new PlaceholderService(placeholderApiUrl, restClient);
    }

    @Test
    void testGetProducts_Success() {
        String productId = "123";
        String expectedResponse = "{\"id\":\"123\",\"name\":\"Product Name\"}";
        String fullUrl = placeholderApiUrl + "/products/" + productId;

        when(restClient.get()
                .uri(fullUrl)
                .retrieve()
                .body(String.class))
                .thenReturn(expectedResponse);

        String actualResponse = placeholderService.getProducts(productId);

        assertEquals(expectedResponse, actualResponse);
        verify(restClient.get().uri(fullUrl).retrieve(), times(1)).body(String.class);
    }

    @Test
    void testGetProducts_InvalidId() {
        String productId = "";
        String fullUrl = placeholderApiUrl + "/products/";

        when(restClient.get()
                .uri(fullUrl)
                .retrieve()
                .body(String.class))
                .thenThrow(new IllegalArgumentException("Invalid ID"));

        assertThrows(IllegalArgumentException.class, () -> placeholderService.getProducts(productId));
        verify(restClient.get().uri(fullUrl).retrieve(), times(1)).body(String.class);
    }
}