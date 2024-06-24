package com.yob.gatewayservice.restcall;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ResolutionException;
import java.lang.reflect.ParameterizedType;

@Component
public class YobRestClient {
    private final transient RestTemplate restTemplate;


    public YobRestClient(@Lazy RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Void> exchange(YobRequestEntity<?> yobRequestEntity){
        return this.restTemplate.exchange(yobRequestEntity.toRequestEntity(), Void.class);
    }

    public <T> ResponseEntity<T> exchange(YobRequestEntity<?> yobRequestEntity , Class<T> responseType){
        return this.restTemplate.exchange(yobRequestEntity.toRequestEntity(),responseType);
    }

    public <T> ResponseEntity<T> exchange(YobRequestEntity<?> yobRequestEntity , ParameterizedTypeReference<T> responseType){
        return this.restTemplate.exchange(yobRequestEntity.toRequestEntity(),responseType);
    }
}
