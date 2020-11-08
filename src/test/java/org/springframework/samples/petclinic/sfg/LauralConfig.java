package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("base")
@Configuration
public class LauralConfig {

    @Bean
    public LaurelWordProducer getLaural(){
        return new LaurelWordProducer();
    }
}
