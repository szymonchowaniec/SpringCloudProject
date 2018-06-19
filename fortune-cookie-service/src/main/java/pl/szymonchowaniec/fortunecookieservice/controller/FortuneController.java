package pl.szymonchowaniec.fortunecookieservice.controller;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
public class FortuneController {

@Autowired
    DiscoveryClient discoveryClient;

    @GetMapping(value = "/fortune")
    public Fortune fortune(){
        String fortune = getDataFromService("DECISION-SERVICE")+" "+getDataFromService("ACTIVITY-SERVICE");
        return new Fortune(fortune);
    }

public String getDataFromService(String service){
    List<ServiceInstance> list = discoveryClient.getInstances(service);

    if(list != null && list.size()>0){
        URI uri = list.get(0).getUri();
        if(uri != null){
            String responseInJSON = (new RestTemplate()).getForObject(uri,String.class);
            return convertJSONResponseToSentence(responseInJSON);
        }
    }
    return null;
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
