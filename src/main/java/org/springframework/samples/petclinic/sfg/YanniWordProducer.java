package org.springframework.samples.petclinic.sfg;


import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("yanni")
@Primary
@Component
public class YanniWordProducer implements wordProducer {

    @Override
    public String getWord() {
        return "Yanni";
    }
}
