package com.yob.gatewayservice.restcall;

import lombok.Generated;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.MultiValueMap;


import java.net.URI;

public final class YobRequestEntity<T> {

    private final T body;
    private final MultiValueMap<String,String> headers;
    private final HttpMethod method;
    private final URI url;

    private final boolean requestBodyLoggingDisabled;
    private final boolean responseBodyLoggingDisabled;

    public RequestEntity<?> toRequestEntity(){
        return new RequestEntity<>(this.body,this.headers,this.method,this.url);
    }

    @Generated
    public T getBody() {
        return body;
    }

    @Generated
    public MultiValueMap<String, String> getHeaders() {
        return headers;
    }

    @Generated
    public HttpMethod getMethod() {
        return method;
    }

    @Generated
    public URI getUrl() {
        return url;
    }

    @Generated
    public boolean isRequestBodyLoggingDisabled() {
        return requestBodyLoggingDisabled;
    }

    @Generated
    public boolean isResponseBodyLoggingDisabled() {
        return responseBodyLoggingDisabled;
    }

    @Generated
    public YobRequestEntity(T body, MultiValueMap<String, String> headers, HttpMethod method, URI url, boolean requestBodyLoggingDisabled, boolean responseBodyLoggingDisabled) {
        this.body = body;
        this.headers = headers;
        this.method = method;
        this.url = url;
        this.requestBodyLoggingDisabled = requestBodyLoggingDisabled;
        this.responseBodyLoggingDisabled = responseBodyLoggingDisabled;
    }

    @Override
    public String toString() {
        return "YobRequestEntity{" +
                "body=" + body +
                ", headers=" + headers +
                ", method=" + method +
                ", url=" + url +
                ", requestBodyLoggingDisabled=" + requestBodyLoggingDisabled +
                ", responseBodyLoggingDisabled=" + responseBodyLoggingDisabled +
                '}';
    }
}
