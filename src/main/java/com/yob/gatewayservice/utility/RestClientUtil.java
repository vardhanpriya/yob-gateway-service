package com.yob.gatewayservice.utility;

import com.yob.gatewayservice.config.PropertyConfig;
import com.yob.gatewayservice.restcall.HttpEntityBuilder;
import com.yob.gatewayservice.restcall.YobRequestEntity;
import com.yob.gatewayservice.restcall.YobRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Configuration
public class RestClientUtil {

    @Autowired
    PropertyConfig propertyConfig;

    @Bean()
    @Lazy
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    YobRestClient yobRestClient;


    //Rest Call Method 3
     public String invokeRestTemplate(String url, HttpEntity<?> httpEntity, HttpMethod httpMethod){
        return callRestTemplate(url,httpEntity,httpMethod,1);
     }
    public String callRestTemplate(String url, HttpEntity<?> httpEntity, HttpMethod httpMethod, int retryCount){

        try{
            ResponseEntity<String> response = restTemplate().exchange(url,httpMethod,httpEntity,String.class);

            return response.getBody();

        }catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException ex){
            if(retryCount < propertyConfig.getRestCallRetryCount()){
                retryCount = retryCount+1;
              return  callRestTemplate(url,httpEntity,httpMethod,retryCount+1);
            }else {
                throw ex;
            }
        }
    }


    // Rest call Method 1
    public <T,R> ResponseEntity<R> callRestApi(String url,HttpEntity<T> httpEntityRequest , HttpMethod method , Class<R> responseType){
        return  restTemplate().exchange(url,method,httpEntityRequest,responseType);
    }



    // Rest Call Method 2
    public String invokeNewRestTemplate(String apiUrl, HttpEntity<?> httpEntity , HttpMethod httpMethod){
        return  callNewRestTemplate(apiUrl,httpEntity,httpMethod,1);
    }

    private String callNewRestTemplate(String apiUrl, HttpEntity<?> httpEntity, HttpMethod httpMethod, int retryCount) {

        String statUrl = apiUrl;
        YobRequestEntity<Object> yobRequestEntity = null;
        try {
            if (apiUrl != null && apiUrl.contains("?")) {
                statUrl = gerUrlWithoutQueryParams(apiUrl);
                yobRequestEntity = HttpEntityBuilder.<Object>of(getTemplateUrl(apiUrl))
                        .withHttpMethod(httpMethod)
                        .withRequestBody(httpEntity.getBody())
                        .withHeaders(httpEntity.getHeaders())
                        .withQueryParams(buildTemplateParams(apiUrl))
                        .build();
            } else {
                yobRequestEntity = HttpEntityBuilder.<Object>of(apiUrl)
                        .withHttpMethod(httpMethod)
                        .withRequestBody(httpEntity.getBody())
                        .withHeaders(httpEntity.getHeaders())
                        .build();
            }
            ResponseEntity<String> response = yobRestClient.exchange(yobRequestEntity, String.class);
            return response.getBody();
        }catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException ex){
            if(retryCount < propertyConfig.getRestCallRetryCount()){
                retryCount = retryCount+1;
                return  callNewRestTemplate(apiUrl,httpEntity,httpMethod,retryCount+1);
            }else {
                throw ex;
            }
        }
    }

    protected String gerUrlWithoutQueryParams(String apiUrl){
          return apiUrl.split("\\?")[0];
    }

    protected String getTemplateUrl(String apiUrl)
    {
        String queryParams = apiUrl.split("\\?")[1];
        String templateUrl = apiUrl.split("\\?")[0];
        String[] split = queryParams.split("&");
        Map<String,String> collect = Arrays.stream(split)
                .collect(Collectors.toMap(k-> k.split("=")[0],v->v.split("=")[1]));

        List<String> keys = collect.keySet().stream().collect(Collectors.toList());
        for(int i =0;i< keys.size();i++){
            if(i==0){
                templateUrl +="?" +keys.get(i) + "={" +keys.get(i) + "}";
            }else {
                templateUrl += "&" +keys.get(i) +"={" +keys.get(i) + "}";
            }
        }
        return templateUrl;
    }

    private MultiValueMap<String,String> buildTemplateParams(String apiUrl) {
        String queryParams = apiUrl.split("\\?")[1];
        String[] split   = queryParams.split("&");

        Map<String,String > map = Arrays.stream(split)
                .collect(Collectors.toMap(k-> k.split("=")[0],v->v.split("=")[1]));

        MultiValueMap<String,String> mvm = new LinkedMultiValueMap<>();

        map.forEach(mvm::add);
            return mvm;

    }


    /*
    For Reference Only

    ResponseEntity<ValidateAaadharOtpRes> responseEntity = restClient.method(Httpethod.POST).uri(propertyConfig.getUrl)
                                       .headers(this::getHeaders).body(validateAAdharOtpReq)
                                       .retrieve.toEntity(ValidateAAdahrOTpRes.class);

                                       ValidateAAdharOtpRes resp = responseEntity.getBody();


     */




}
