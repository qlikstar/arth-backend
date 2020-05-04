package com.decipherx.projectarth.scheduler;

import com.decipherx.projectarth.scheduler.service.SchedulerService;
import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.Date;

@Singleton
public class ScheduleConfig {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleConfig.class);

    protected final SchedulerService schedulerService;

    public ScheduleConfig(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Scheduled(fixedDelay = "30s")
    void executeEveryMinute() {
        schedulerService.run();
        LOG.info("Simple Job every 60 seconds: {}", new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(new Date()));
    }
}
