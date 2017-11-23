package com.example.democonsumer.web;

import com.example.democonsumer.remote.FeignServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    FeignServiceClient feignServiceClient;

    @RequestMapping("/listCity")
    public List listCity() {
        List result = restTemplate.getForObject("http://spring-cloud-producer/city/list", List.class);
        return result;

    }
    @RequestMapping("/queryCity")
    @HystrixCommand(fallbackMethod = "queryCityFallback")
    public List queryCityByName(String name) {
        List result = feignServiceClient.queryCityByName(name);
        return result;

    }
    public List queryCityFallback(String name) {
        System.out.println(name+"error!");
        return new ArrayList();
    }
}