package org.springframework.samples.petclinic.sfg.Junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.samples.petclinic.sfg.LaurelWordProducer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = HearingInterpreterInnerConfigTest.testConfig.class)
class HearingInterpreterInnerConfigTest {

    @Configuration
    static class testConfig{
        @Bean
        HearingInterpreter hearingInterpreter(){
           return new HearingInterpreter(new LaurelWordProducer());
        }
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHear() {
        String word= hearingInterpreter.WhatIHear();

        assertEquals("Laurel",word);
    }
}