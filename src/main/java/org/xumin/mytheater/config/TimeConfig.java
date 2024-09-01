package org.xumin.mytheater.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xumin.mytheater.bean.TimeUtils;

@Configuration
public class TimeConfig {
    @Bean
    public TimeUtils timeUtils() {
        return new TimeUtils();
    }
}
