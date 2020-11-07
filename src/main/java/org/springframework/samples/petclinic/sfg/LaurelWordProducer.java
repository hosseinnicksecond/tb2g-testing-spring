package org.springframework.samples.petclinic.sfg;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class LaurelWordProducer implements wordProducer {

    @Override
    public String getWord() {
        return "Laurel";
    }
}
