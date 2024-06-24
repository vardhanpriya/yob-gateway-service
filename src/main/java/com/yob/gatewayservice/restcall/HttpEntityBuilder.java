package com.yob.gatewayservice.restcall;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HttpEntityBuilder <T>{
    private transient  T requestBody;
    private final transient HttpHeaders headers = new HttpHeaders();

    private transient String uri;
    private transient HttpMethod httpMethod;
    private final transient MultiValueMap<String,String> queryParams = new LinkedMultiValueMap<>();
    private final transient Map<String,Object> pathParams = new HashMap<>();
    private transient boolean requestBodyLoggingDisabled;
    private transient boolean responseBodyLoggingDisabled;

    private HttpEntityBuilder(){

    }
    public static <T> HttpEntityBuilder<T> of(String url){
        return ( new HttpEntityBuilder()).withUrl(url);
    }

    public HttpEntityBuilder<T> withQueryParams(MultiValueMap<String , String> queryParams){
       this.queryParams.putAll(queryParams);
       return this;
    }
    public HttpEntityBuilder<T> withHeaders(MultiValueMap<String,String> headers){
       this.headers.addAll(headers);
       return this;
    }
    public HttpEntityBuilder<T> withRequestBody(T requestBody){
       this.requestBody = requestBody;
       return this;
    }

    public HttpEntityBuilder<T> withHttpMethod(HttpMethod httpMethod){
       this.httpMethod = httpMethod;
       return this;
    }
    public HttpEntityBuilder<T> withUrl(String url){
        this.uri = url;
        return this;
    }

    public YobRequestEntity<T> build(){
       URI uri = UriComponentsBuilder.fromUriString(this.uri).replaceQueryParams(this.queryParams).build(this.pathParams);
       this.checkMediaTypeProvided();
       return new YobRequestEntity<>(this.requestBody,this.headers,this.httpMethod,uri,this.requestBodyLoggingDisabled,this.responseBodyLoggingDisabled);
    }

    private void checkMediaTypeProvided(){
       if(this.httpMethod == null){
           throw new IllegalArgumentException("Http verb missing");
       }
    }
}
