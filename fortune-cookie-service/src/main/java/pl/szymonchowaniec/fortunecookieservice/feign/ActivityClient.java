package pl.szymonchowaniec.fortunecookieservice.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ACTIVITY-SERVICE", fallback = ActivityFallback.class)
@RibbonClient(name = "ACTIVITY-SERVICE")
public interface ActivityClient {
    @GetMapping("/")
    String getResponse();
}
