package com.atguigu.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    //@Scheduled(cron = "0 * * * * SUN-SAT" )
    //@Scheduled(cron = "0,1,2,3,4 * * * * SUN-SAT" )
    //@Scheduled(cron = "0-4 * * * * SUN-SAT" )
    @Scheduled(cron = "0/4 * * * * SUN-SAT" )
    public void hello(){
        System.out.println("hello ....");
    }
}
