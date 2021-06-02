package com.example.meetime.Utils;

import com.example.meetime.Config.AddQueryParameterInterceptor;
import com.example.meetime.Domain.Response.PipedriveResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Component
public class PipedriveRestConnection {

    @Autowired
    private ObjectMapper objectMapper;

    private RestTemplate restTemplate;

    private final static String TOKEN = "c7f2c93ce077554996eab049843f0132c2609927";
    private final static String BASE_URL = "https://api.pipedrive.com/v1";

    public PipedriveRestConnection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(BASE_URL));
        this.restTemplate.getInterceptors().add(new AddQueryParameterInterceptor(TOKEN));
    }

    public <T> PipedriveResponse<T> post(String url, Object object, Class<T> type) {
        PipedriveResponse pipedriveResponse = restTemplate.postForObject(url, object, PipedriveResponse.class);
        T data = objectMapper.convertValue(pipedriveResponse.getData(), type);
        pipedriveResponse.setData(data);
        return pipedriveResponse;
    }

    public PipedriveResponse get(String url) {
        return restTemplate.getForObject(url, PipedriveResponse.class);
    }

}
