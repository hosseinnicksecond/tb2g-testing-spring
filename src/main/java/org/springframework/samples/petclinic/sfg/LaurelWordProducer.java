package org.springframework.samples.petclinic.sfg;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//@Profile("laurel")
@Component
public class LaurelWordProducer implements wordProducer {

    @Override
    public String getWord() {
        return "Laurel";
    }
}
