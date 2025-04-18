package com.charlie.cache.cacheCli;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PlaceholderService {

    private String cacheApiFullPath = "";
    private final RestClient restClient;

    PlaceholderService(@Value("${placeholder.api.url}") String cacheApiUrl, RestClient restClient) {
        String cacheApiProductPath = "/products";
        this.cacheApiFullPath = cacheApiUrl + cacheApiProductPath;
        this.restClient = restClient;
    }

    public String getProducts(String id) {
        String url = cacheApiFullPath + "/" + id;
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }
}
