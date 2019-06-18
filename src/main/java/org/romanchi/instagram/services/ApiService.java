package org.romanchi.instagram.services;

import org.romanchi.instagram.api.ApiClient;
import org.romanchi.instagram.exceptions.ApiException;
import org.romanchi.instagram.model.dto.GirlDTO;
import org.romanchi.instagram.repositories.GirlsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    private final ApiClient apiClient;

    @Autowired
    public ApiService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public GirlDTO next(){
        return apiClient.nextGirl().orElseThrow(()->new ApiException("ApiException"));
    }

    public GirlDTO current() {
        return apiClient.currentGirl().orElseThrow(()->new ApiException("ApiException"));
    }

    public GirlDTO like() {
        return apiClient.likeGirl().orElseThrow(()->new ApiException("ApiException"));
    }
}
