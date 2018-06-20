package pl.szymonchowaniec.fortunecookieservice.controller;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.szymonchowaniec.fortunecookieservice.feign.ActivityClient;
import pl.szymonchowaniec.fortunecookieservice.feign.DecisionClient;


@RestController
public class FortuneController {

    @Autowired
    private ActivityClient activityClient;
    @Autowired
    private DecisionClient decisionClient;

    @GetMapping(value = "/fortune")
    public Fortune fortune(){
        String fortune = convertJSONResponseToSentence(decisionClient.getResponse())+" "+ convertJSONResponseToSentence(activityClient.getResponse());
        return new Fortune(fortune);
    }


private String convertJSONResponseToSentence(String responseInJSON){
    JSONArray dataFromService = JsonPath.read(responseInJSON,"$..*");
    return (String) dataFromService.get(0);
}


    class Fortune{
        private String fortune;
        public Fortune(String fortune){
            this.fortune = fortune;
        }

        public String getFortune() {
            return fortune;
        }
    }
}
