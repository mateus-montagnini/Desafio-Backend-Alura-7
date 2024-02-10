package com.mrocha.desafio.conf;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConf {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
