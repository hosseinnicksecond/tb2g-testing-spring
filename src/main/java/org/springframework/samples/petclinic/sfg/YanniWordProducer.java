package org.springframework.samples.petclinic.sfg;


import org.springframework.stereotype.Component;

@Component
public class YanniWordProducer implements wordProducer {

    @Override
    public String getWord() {
        return "Yanni";
    }
}
