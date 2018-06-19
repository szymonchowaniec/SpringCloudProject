package pl.szymonchowaniec.activityservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

    @Value("${activities}")
    private String activities;

    @GetMapping(value = "/")
    public Activity activity(){
        return getActivity();
    }

    private Activity getActivity() {
        String [] sentende = activities.split(",");
        int i = (int) Math.round(Math.random()*(sentende.length - 1));
        return new Activity(sentende[i]);
    }


    class Activity{
        private String activity;
        public Activity(String activity){
            this.activity = activity;
        }

        public String getActivity() {
            return activity;
        }
    }
}
