package pl.szymonchowaniec.fortunecookieservice.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "DECISION-SERVICE", fallback = DecisionFallback.class)
@RibbonClient(name = "DECISION-SERVICE")
public interface DecisionClient {
    @GetMapping("/")
    String getResponse();
}