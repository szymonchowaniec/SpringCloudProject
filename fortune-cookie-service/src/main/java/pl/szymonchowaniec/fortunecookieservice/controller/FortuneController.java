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


@RestController
public class FortuneController {

    @Autowired
    @Qualifier("ribbonRestTemplate")
    RestTemplate ribbonRestTemplate;

    @GetMapping(value = "/fortune")
    public Fortune fortune(){
        String fortune = getDataFromService("DECISION-SERVICE")+" "+getDataFromService("ACTIVITY-SERVICE");
        return new Fortune(fortune);
    }

public String getDataFromService(String service){

            String responseInJSON = (ribbonRestTemplate).getForObject("http://"+service,String.class);
            return convertJSONResponseToSentence(responseInJSON);
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
