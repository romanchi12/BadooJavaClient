package org.romanchi.instagram.services;

import org.romanchi.instagram.api.ApiClient;
import org.romanchi.instagram.exceptions.ApiException;
import org.romanchi.instagram.model.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    final
    private ApiClient apiClient;

    @Autowired
    public ApiService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Girl next(){
        return apiClient.nextGirl().orElseThrow(()->new ApiException("ApiException"));
    }

    public Girl current() {
        return apiClient.currentGirl().orElseThrow(()->new ApiException("ApiException"));
    }
}
