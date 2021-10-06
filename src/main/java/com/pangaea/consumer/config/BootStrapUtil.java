package com.pangaea.consumer.config;

import com.pangaea.consumer.service.interfaces.StrategyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class BootStrapUtil {

    @Bean(name = "strategyHandlerList")
    public List<StrategyHandler> strategyHandlers(List<StrategyHandler> strategyHandlerList){
        List<StrategyHandler> handlerList = new ArrayList<>();
        handlerList.forEach(handler -> handlerList.add(handler));
        return handlerList;
    }

}
