package com.example.democonsumer.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Component
@FeignClient(value = "spring-cloud-producer")
public interface FeignServiceClient {
    @RequestMapping(value = "/city/queryByName")
    List queryCityByName(@RequestParam("name") String name);
}
