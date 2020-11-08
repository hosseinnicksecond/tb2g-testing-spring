package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class YanniConfig {

    @Bean
//    @Primary
    public YanniWordProducer getYanni(){
        return new YanniWordProducer();
    }
}
